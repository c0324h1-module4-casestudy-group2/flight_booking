new WOW().init();

flatpickr(".date-flatpickr", {
    dateFormat: 'd-m-Y',
    minDate: 'today',
    maxDate: new Date().fp_incr(14)
});


// Cuộn trang lên đầu khi nhấn nút
$('scrollToTop').click(() => {
    window.scrollTo({
        top: 0,
        behavior: 'smooth'
    });
});

$('.failed').click(() => {
    Swal.fire({
        icon: "warning",
        iconColor: "#ffc107",
        title: "Tính năng đang phát triển!",
        confirmButtonText: "Đóng",
        color: "#003366"
    })
})





