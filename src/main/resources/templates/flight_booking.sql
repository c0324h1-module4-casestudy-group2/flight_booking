CREATE DATABASE flight_booking;
USE flight_booking;

CREATE TABLE accounts
(
    accountId INT AUTO_INCREMENT PRIMARY KEY,
    username  VARCHAR(50)  NOT NULL UNIQUE,
    password  VARCHAR(255) NOT NULL,
    role      VARCHAR(50)  NOT NULL
);

CREATE TABLE customers
(
    customerId  INT AUTO_INCREMENT PRIMARY KEY,
    name        VARCHAR(100) NOT NULL,
    email       VARCHAR(100) NOT NULL UNIQUE,
    phoneNumber VARCHAR(15),
    accountId   INT,
    FOREIGN KEY (accountId) REFERENCES accounts (accountId)
);

CREATE TABLE admins
(
    adminId   INT AUTO_INCREMENT PRIMARY KEY,
    name      VARCHAR(100) NOT NULL,
    email     VARCHAR(100) NOT NULL UNIQUE,
    accountId INT,
    FOREIGN KEY (accountId) REFERENCES accounts (accountId)
);

CREATE TABLE airports
(
    airportId   INT AUTO_INCREMENT PRIMARY KEY,
    airportCode VARCHAR(5) NOT NULL UNIQUE,
    airportName VARCHAR(100),
    city        VARCHAR(50),
    country     VARCHAR(50)
);

CREATE TABLE flights
(
    flightId           INT AUTO_INCREMENT PRIMARY KEY,
    flightNumber       VARCHAR(10) NOT NULL UNIQUE,
    airline            VARCHAR(50),
    departureAirportId INT,
    arrivalAirportId   INT,
    departureTime      DATETIME,
    arrivalTime        DATETIME,
    seatCapacity       INT,
    price              DECIMAL(10, 2),
    adminId            INT,
    FOREIGN KEY (departureAirportId) REFERENCES airports (airportId),
    FOREIGN KEY (arrivalAirportId) REFERENCES airports (airportId),
    FOREIGN KEY (adminId) REFERENCES admins (adminId)
);

CREATE TABLE bookings
(
    bookingId     INT AUTO_INCREMENT PRIMARY KEY,
    customerId    INT,
    flightId      INT,
    bookingDate   DATETIME,
    numberOfSeats INT,
    totalPrice    DECIMAL(10, 2),
    status        VARCHAR(50),
    FOREIGN KEY (customerId) REFERENCES customers (customerId),
    FOREIGN KEY (flightId) REFERENCES flights (flightId)
);

CREATE TABLE payments
(
    paymentId     INT AUTO_INCREMENT PRIMARY KEY,
    bookingId     INT,
    paymentDate   DATETIME,
    amount        DECIMAL(10, 2),
    paymentMethod VARCHAR(50),
    paymentStatus VARCHAR(50),
    FOREIGN KEY (bookingId) REFERENCES bookings (bookingId)
);

CREATE TABLE seats
(
    seatId             INT AUTO_INCREMENT PRIMARY KEY,
    flightId           INT,
    seatNumber         VARCHAR(10),
    class              VARCHAR(50),
    availabilityStatus VARCHAR(50),
    FOREIGN KEY (flightId) REFERENCES flights (flightId)
);

CREATE TABLE bookingDetails
(
    bookingDetailId INT AUTO_INCREMENT PRIMARY KEY,
    bookingId       INT,
    seatId          INT,
    name            VARCHAR(100),
    age             INT,
    gender          VARCHAR(10),
    FOREIGN KEY (bookingId) REFERENCES bookings (bookingId),
    FOREIGN KEY (seatId) REFERENCES seats (seatId)
);

#admins: cập nhật thông tin chuyến bay
# customers:thông tin về khách hàng.
# airports:thông tin về các sân bay.
# flights:hông tin về các chuyến bay, liên kết với bảng airports thông qua các khóa ngoại departureAirportId và arrivalAirportId.
# bookings:thông tin về các đặt chỗ, liên kết với bảng customers và flights thông qua các khóa ngoại customerId và flightId.
# payments:thông tin về các khoản thanh toán, liên kết với bảng bookings thông qua khóa ngoại bookingId.
# seats:thông tin về chỗ ngồi trên các chuyến bay, liên kết với bảng flights thông qua khóa ngoại flightId.
# bookingDetails:thông tin chi tiết về các đặt chỗ, liên kết với bảng bookings và seats thông qua các khóa ngoại bookingId và seatId.