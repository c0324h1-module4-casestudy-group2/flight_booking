ocument.addEventListener('DOMContentLoaded', function () {
    var flightModal = document.getElementById('flightModal');
    flightModal.addEventListener('show.bs.modal', function (event) {
        var button = event.relatedTarget;
        var flightId = button.getAttribute('data-flight-id');
        var modalTitle = flightModal.querySelector('.modal-title');
        var from = flightModal.querySelector('#from');
        var to = flightModal.querySelector('#to');
        var date = flightModal.querySelector('#date');
        var time = flightModal.querySelector('#time');
        var status = flightModal.querySelector('#status');

        var flightData = {
            1: { from: 'Hà Nội (HAN)', to: 'Hồ Chí Minh (SGN)', date: '15/07/2023', time: '10:30 AM', status: 'Đã xác nhận' },
            2: { from: 'Đà Nẵng (DAD)', to: 'Hà Nội (HAN)', date: '20/06/2023', time: '2:45 PM', status: 'Đã xác nhận' },
            3: { from: 'Hồ Chí Minh (SGN)', to: 'Phú Quốc (PQC)', date: '05/08/2023', time: '8:00 AM', status: 'Đang chờ' },
            4: { from: 'Nha Trang (CXR)', to: 'Đà Lạt (DLI)', date: '10/05/2023', time: '11:15 AM', status: 'Đã hủy' }
        };

        var flight = flightData[flightId];
        modalTitle.textContent = 'Chi tiết chuyến bay';
        from.textContent = flight.from;
        to.textContent = flight.to;
        date.textContent = flight.date;
        time.textContent = flight.time;
        status.textContent = flight.status;
    });
});