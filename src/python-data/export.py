import requests


import json
import xml.etree.ElementTree as ET
from pathlib import Path

# ---------- CONFIG ----------
MATCHES_OUT = "matches.json"
TEAMS_OUT = "teams.json"
# ----------------------------
# SOAP request URL
def export():
  url = "http://localhost:8080/ws"

  # structured XML
  payload = f"""<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/"
            xmlns:gs="match.pltracker.com">
                      <soapenv:Header/>
                      <soapenv:Body>
                          <gs:getAllDataRequest>
                          </gs:getAllDataRequest>
                      </soapenv:Body>
              </soapenv:Envelope>"""
  # headers
  headers = {
      'Content-Type': 'text/xml; charset=utf-8'
  }
  # POST request
  response = requests.request("POST", url, headers=headers, data=payload)

  return response.text

export()


# Helper: local name of an element tag (strips namespace)
def local_name(tag: str) -> str:
    if tag is None:
        return ""
    if "}" in tag:
        return tag.split("}", 1)[1]
    return tag

xml_data = export()

# Parse
root = ET.fromstring(xml_data)

# Find all <match> elements anywhere in the document (namespace-agnostic)
matches_elems = [el for el in root.iter() if local_name(el.tag) == "match"]

matches = []
for m in matches_elems:
    obj = {}
    # iterate direct children (elements) of the match node
    for child in list(m):
        key = local_name(child.tag)
        val = child.text.strip() if child.text and child.text.strip() else None

        # Convert numeric-looking values to int when sensible
        if val is not None:
            # treat match_id and scores as integers if possible
            if key in ("match_id", "home_score", "away_score", "match_week"):
                try:
                    obj[key] = int(val)
                except ValueError:
                    obj[key] = val
            else:
                obj[key] = val
        else:
            obj[key] = None

    matches.append(obj)

# Find team entries.
# The response contains a container <...:team> then multiple <...:team> children.
# We locate 'team' elements that contain a 'team_id' child (to avoid picking the outer wrapper).
team_elems = []
for el in root.iter():
    if local_name(el.tag) == "team":
        # check if this element has a child named team_id (so it's a team record)
        has_id_child = any(local_name(c.tag) == "team_id" for c in list(el))
        if has_id_child:
            team_elems.append(el)

teams = []
for t in team_elems:
    obj = {}
    for child in list(t):
        key = local_name(child.tag)
        val = child.text.strip() if child.text and child.text.strip() else None

        # convert team_id to int if possible
        if val is not None and key == "team_id":
            try:
                obj[key] = int(val)
            except ValueError:
                obj[key] = val
        else:
            obj[key] = val
    teams.append(obj)

# Optional: simple dedupe (if identical team elements appear twice)
# Create dict keyed by team_id if present, otherwise by teamName
def dedupe_by_key(list_of_dicts, key_candidates):
    seen = {}
    for d in list_of_dicts:
        key = None
        for k in key_candidates:
            if k in d and d[k] is not None:
                key = str(d[k])
                break
        if key is None:
            # fallback - use full tuple
            key = tuple(sorted(d.items()))
        if key not in seen:
            seen[key] = d
    return list(seen.values())

teams = dedupe_by_key(teams, ["team_id", "teamName"])

# Write JSON files
with open(MATCHES_OUT, "w", encoding="utf-8") as fh:
    json.dump(matches, fh, ensure_ascii=False, indent=2)

with open(TEAMS_OUT, "w", encoding="utf-8") as fh:
    json.dump(teams, fh, ensure_ascii=False, indent=2)

# Summary print
print(f"Wrote {len(matches)} matches to {MATCHES_OUT}")
print(f"Wrote {len(teams)} teams   to {TEAMS_OUT}")
