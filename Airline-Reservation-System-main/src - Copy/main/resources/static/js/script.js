const BASE_URL = "http://localhost:8080";

/* SEARCH */
async function searchFlights() {

    const source = document.getElementById("source").value;
    const destination = document.getElementById("destination").value;

    document.getElementById("loader").style.display = "block";

    const res = await fetch(`${BASE_URL}/api/flights/search?source=${source}&destination=${destination}`);
    const data = await res.json();

    document.getElementById("loader").style.display = "none";

    let html = "";

    data.forEach((f, index) => {
        html += `
        <div class="flight-card" style="animation-delay:${index * 0.1}s">

            <div>
                <h2>${f.airline}</h2>
                <p class="route">${f.source} → ${f.destination}</p>
                <p class="time">🕒 Non-stop • 2h 30m</p>
            </div>

            <div>
                <p>Seats Left: ${f.seatsAvailable}</p>
            </div>

            <div class="flight-right">
                <h2 class="price">₹${f.price}</h2>
                <button onclick="goToBooking('${f.id}')">Book Now</button>
            </div>

        </div>`;
    });

    document.getElementById("results").innerHTML = html;
}
function goHome() {
    window.location.href = "index.html";
}

/* BACK */
function goBack() {
    window.history.back();
}

/* LOGIN */
async function login() {

    const res = await fetch(`${BASE_URL}/users/login`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            email: document.getElementById("email").value,
            password: document.getElementById("password").value
        })
    });

    const data = await res.json();

    localStorage.setItem("token", data.token);

    document.body.innerHTML = "<h2 style='text-align:center;margin-top:50px;'>✅ Login Successful</h2>";

    setTimeout(() => {
        window.location.href = "index.html";
    }, 1500);
}

/* SIGNUP */
async function signup() {

    const res = await fetch(`${BASE_URL}/users/register`, {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify({
            name: document.getElementById("name").value,
            email: document.getElementById("email").value,
            password: document.getElementById("password").value
        })
    });

    if (res.ok) {
        alert("Signup successful");
        window.location.href = "login.html";
    } else {
        alert("Signup failed");
    }
}

/* BOOK */
function goToBooking(id) {
    localStorage.setItem("flightId", id);
    window.location.href = "booking.html";
}

/* CONFIRM BOOK */
async function confirmBooking() {

    const token = localStorage.getItem("token");

    const res = await fetch(`${BASE_URL}/booking`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json",
            "Authorization": "Bearer " + token
        },
        body: JSON.stringify({
            flightId: parseInt(localStorage.getItem("flightId")),
            seats: parseInt(document.getElementById("seats").value)
        })
    });

    if (res.ok) {
        document.body.innerHTML = `
            <div style="text-align:center;margin-top:50px;">
                <h2>🎉 Booking Confirmed!</h2>
                <button onclick="window.location.href='history.html'">View My Bookings</button>
            </div>`;
    } else {
        alert("Booking failed");
    }
}