let factor = 0.35; // initialer Standardfaktor

function setFactor(newFactor, buttonId) {
    factor = newFactor;

    document.getElementById("kmButton").classList.remove("selected");
    document.getElementById("minButton").classList.remove("selected");
    document.getElementById(buttonId).classList.add("selected");
}

async function multiply() {
    const number = document.getElementById("number").value;
    if (isNaN(number)) {
        document.getElementById("result").innerText = "Bitte eine gültige Zahl eingeben!";
        return;
    }

    const response = await fetch(`/api/multiply?number=${number}&factor=${factor}`);
    if (response.ok) {
        const result = await response.text();
        document.getElementById("result").innerText = `Zu zahlen sind: ${result} €`;
    } else {
        document.getElementById("result").innerText = "Keine gültige Zahl eingegeben!";
    }
}
