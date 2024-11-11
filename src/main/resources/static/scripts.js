let factor = 0.35;
let userName = null;

async function login() {
    userName = document.getElementById("username").value;
    const response = await fetch(`/api/login?name=${userName}`, { method: 'POST' });
    if (response.ok) {
        document.getElementById("calc-section").style.display = 'block';
    }
    clearFields()
    loadCalculations()
}

function setFactor(newFactor, buttonId) {
    factor = newFactor;
    document.getElementById("kmButton").classList.remove("selected");
    document.getElementById("minButton").classList.remove("selected");
    document.getElementById(buttonId).classList.add("selected");
}

async function multiply() {
    const number = document.getElementById("number").value;
    if (isNaN(number)) {
        document.getElementById("result").innerText = "Bitte geben sie eine gültige Zahl ein!";
        return;
    }

    const response = await fetch(`/api/multiply?number=${number}&factor=${factor}`);
    if (response.ok) {
        const result = await response.text();
        document.getElementById("result").innerText = `Zu zahlen sind: ${result} €`;
    } else {
        document.getElementById("result").innerText = "Fehler beim berechnen des Ergebnisses!";
    }
    saveCalculation()
    loadCalculations()
}

async function saveCalculation() {
    const number = document.getElementById("number").value;
    const response = await fetch(`/api/calculate?name=${userName}&number=${number}&factor=${factor}`, { method: 'POST' });
    const calculation = await response.json();
}

async function loadCalculations() {
    const response = await fetch(`/api/calculations?name=${userName}`);
    const calculations = await response.json();
    const list = document.getElementById("calculation-list");
    list.innerHTML = '';
    calculations.forEach(calc => {
        const item = document.createElement("li");
        item.innerText = `Nummer: ${calc.number}, Faktor: ${calc.factor}, Zu zahlen: ${calc.result} €`;
        list.appendChild(item);
    });
}

function clearFields() {
    document.getElementById("result").innerText = "";
    document.getElementById("number").value = "";
}

function searchForEnterUsername() {
    if(event.key === "Enter") {
        login()
   }
}

function searchForEnterNums() {
    if(event.key === "Enter") {
        multiply()
    }
}
