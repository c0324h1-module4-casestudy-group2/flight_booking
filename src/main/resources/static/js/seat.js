function updateSummary() {
    const selected = document.querySelectorAll('.seat.selected');
    const selectedCountValue = selected.length;
    const selectedSeats = Array.from(selected).map(seat => seat.textContent).join(', ');
    const seatPrice = 500000; // Giá ghế

    document.getElementById('selected-count').textContent = selectedCountValue;
    document.getElementById('selected-seats').textContent = selectedSeats;
    document.getElementById('total-price').textContent = (selectedCountValue * seatPrice).toLocaleString('vi-VN') + ' VND';

    const seatIds = Array.from(selected).map(seat => seat.dataset.seatId);
    document.getElementById('seat-ids').value = seatIds.join(',');
}

function toggleSeat(seatElement) {
    if (seatElement.classList.contains('unavailable')) return;

    seatElement.classList.toggle('selected');
    updateSummary();
}