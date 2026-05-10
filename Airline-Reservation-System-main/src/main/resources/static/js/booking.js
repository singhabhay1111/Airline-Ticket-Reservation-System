const urlParams = new URLSearchParams(window.location.search);
const flightId = urlParams.get("flightId");

async function confirmBooking() {

    const token = localStorage.getItem("token");

    const name = document.getElementById("name").value;
    const age = document.getElementById("age").value;

    const res = await fetch("http://localhost:8080/booking", {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify({
            name: name,
            age: age,
            flightId: flightId
        })
    });

    if(res.ok){
        alert("Booking Confirmed!");
        window.location.href = "history.html";
    } else {
        alert("Booking Failed");
    }
}