const API_BASE = "http://localhost:8080";

const teamSelection = document.querySelector('#team-select');
const tableBody = document.querySelector('#table-body')
teamSelection.addEventListener('change', async () => {
    tableBody.innerHTML = ''
    const response = await fetch(`http://localhost:8080/team/${teamSelection.value}`)
    if (!response.ok) throw new Error("Failed to fetch requests")
    const requests = await response.json()
    requests.forEach(element => {
        create_elements(element)
    });
})

function create_elements(element) {
    const row = document.createElement("tr")
    const date = document.createElement("td")
    date.textContent = element.match_date
    const home = document.createElement("td")
    home.textContent = element.homeTeam
    const away = document.createElement("td")
    away.textContent = element.away_team
    const score = document.createElement("td")
    score.textContent = `${element.home_score}-${element.away_score}`
    const win = document.createElement("td")
    if(element.homeTeam == teamSelection.value && element.home_score > element.away_score){
        win.textContent = "Win"
    }
    else if(element.away_team == teamSelection.value && element.home_score < element.away_score){
        win.textContent = "Win"
    }
    else if(element.home_score == element.away_score){
        win.textContent = "Draw"
    }
    else {
        win.textContent = "Lose"
    }
    row.appendChild(date)
    row.appendChild(home)
    row.appendChild(score)
    row.appendChild(away)
    row.appendChild(win)
    tableBody.appendChild(row)
}