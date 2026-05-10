const BASE_URL = "http://localhost:8080";

// ================= NAVIGATION =================
function goHome() {
    window.location.href = "index.html";
}

function goBack() {
    window.history.back();
}

// ================= LOAD BOOKINGS =================
async function loadBookings() {
    try {
        const token = localStorage.getItem("token");

        if (!token) {
            alert("Please login first!");
            window.location.href = "login.html";
            return;
        }

        const response = await fetch(`${BASE_URL}/booking/my`, {
            method: "GET",
            headers: {
                "Content-Type": "application/json",
                "Authorization": "Bearer " + token
            }
        });

        if (!response.ok) {
            throw new Error("Failed to fetch bookings");
        }

        const data = await response.json();

        const tableBody = document.querySelector("#bookingTable tbody");
        const emptyMsg = document.getElementById("emptyMsg");

        tableBody.innerHTML = "";
        emptyMsg.innerText = "";

        if (!data || data.length === 0) {
            emptyMsg.innerText = "No bookings found.";
            return;
        }

        data.forEach(booking => {
            const row = document.createElement("tr");

            row.innerHTML = `
                <td>${booking.id}</td>
                <td>${booking.flightName || "N/A"}</td>
                <td>${booking.source}</td>
                <td>${booking.destination}</td>
                <td>${booking.date}</td>
            `;

            tableBody.appendChild(row);
        });

    } catch (error) {
        console.error("Error:", error);
        alert("Error loading bookings");
    }
}

// ================= INIT =================
window.onload = loadBookings;