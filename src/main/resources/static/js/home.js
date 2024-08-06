flatpickr(".date-flatpickr", {
    dateFormat: 'd-m-Y',
    minDate: 'today',
    maxDate: new Date().fp_incr(14)
});

const showMoreButton = document.getElementById('show-more');
const hiddenCards = document.querySelectorAll('.hidden');
let hiddenIndex = 0;

showMoreButton.addEventListener('click', () => {
    // Hiển thị tối đa 6 chuyến bay mỗi lần nhấn
    for (let i = 0; i < 6 && hiddenIndex < hiddenCards.length; i++) {
        hiddenCards[hiddenIndex].classList.remove('hidden');
        hiddenIndex++;
    }

    // Ẩn nút nếu không còn chuyến bay nào
    if (hiddenIndex >= hiddenCards.length) {
        showMoreButton.classList.add('hidden');
    }
});

// Xử lý tìm kiếm
document.getElementById('search-form').addEventListener('submit', function (event) {
    event.preventDefault();
    // Thêm logic tìm kiếm chuyến bay
    alert("Tìm kiếm chuyến bay từ " + document.getElementById('departure').value + " đến " + document
        .getElementById('destination').value + " vào ngày " + document.getElementById('date').value);
});

// Xử lý tra cứu đặt chỗ
document.getElementById('checkBookingForm').addEventListener('submit', function (event) {
    event.preventDefault();
    // Thêm logic tra cứu đặt chỗ
    document.getElementById('info').innerText = "Thông tin vé cho mã đặt chỗ: " + document
        .getElementById('booking-code').value;
    document.getElementById('booking-info').classList.remove('hidden');
});


// Lấy nút cuộn
const scrollToTopButton = document.getElementById('scrollToTop');

// Hiển thị hoặc ẩn nút cuộn khi cuộn trang
window.addEventListener('scroll', () => {
    if (window.scrollY > 300) {
        scrollToTopButton.style.display = 'flex';
    } else {
        scrollToTopButton.style.display = 'none';
    }
});

// Cuộn trang lên đầu khi nhấn nút
scrollToTopButton.addEventListener('click', () => {
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    });
});



