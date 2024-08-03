CREATE DATABASE flight_booking;
USE flight_booking;

create table roles
(
    id          int         not null auto_increment primary key,
    name        varchar(50) not null,
    description varchar(50) not null
);

create table users
(
    id             int              not null auto_increment primary key,
    fullname       varchar(100)     not null,
    username       varchar(100)     not null unique,
    password       varchar(200)     not null,
    email          varchar(100)     not null unique,
    address        varchar(200)     null,
    phone          varchar(12)      null,
    avatar         varchar(200)     null,
    activated      bit(1) default 1 not null,
    remember_token varchar(200)
);

create table users_roles
(
    id      int primary key not null auto_increment,
    user_id int             not null,
    role_id int             not null,
    constraint users_roles_roles_fk foreign key (role_id) references roles (id),
    constraint users_roles_users_fk foreign key (user_id) references users (id)
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
    userId             INT,
    FOREIGN KEY (departureAirportId) REFERENCES airports(airportId),
    FOREIGN KEY (arrivalAirportId) REFERENCES airports(airportId),
    FOREIGN KEY (userId) REFERENCES users(id)
);

CREATE TABLE seats
(
    seatId             INT AUTO_INCREMENT PRIMARY KEY,
    flightId           INT NOT NULL,
    seatNumber         VARCHAR(10) NOT NULL,
    class              VARCHAR(50) NOT NULL,
    availabilityStatus ENUM('available', 'booked', 'reserved', 'unavailable') NOT NULL DEFAULT 'available',
    holdExpiration     DATETIME,
    FOREIGN KEY (flightId) REFERENCES flights(flightId)
);

CREATE TABLE bookings
(
    bookingId     INT AUTO_INCREMENT PRIMARY KEY,
    userId        INT,
    flightId      INT,
    bookingDate   DATETIME,
    numberOfSeats INT,
    totalPrice    DECIMAL(10, 2),
    status        VARCHAR(50),
    name          VARCHAR(100),
    age           INT,
    gender        VARCHAR(10),
    country       VARCHAR(50),
    code_booking  VARCHAR(6),
    FOREIGN KEY (userId) REFERENCES users (id),
    FOREIGN KEY (flightId) REFERENCES flights (flightId)
);

CREATE TABLE pay_method
(
    paymethod_id   INT PRIMARY KEY AUTO_INCREMENT,
    paymethod_name VARCHAR(100)
);

CREATE TABLE payments
(
    paymentId     INT AUTO_INCREMENT PRIMARY KEY,
    bookingId     INT,
    paymethod_id  INT,
    paymentDate   DATETIME,
    amount        DECIMAL(10, 2),
    paymentStatus VARCHAR(50),
    FOREIGN KEY (bookingId) REFERENCES bookings (bookingId),
    FOREIGN KEY (paymethod_id) REFERENCES pay_method (paymethod_id)
);

CREATE TABLE order_detail
(
    order_id  INT PRIMARY KEY AUTO_INCREMENT,
    userId    INT,
    bookingId INT,
    FOREIGN KEY (userId) REFERENCES users (id),
    FOREIGN KEY (bookingId) REFERENCES bookings (bookingId)
);

insert into roles (name, description)
values ('ROLE_ADMIN', 'Quản trị viên'),
       ('ROLE_EMPLOYEE', 'Nhân viên'),
       ('ROLE_CUSTOMER', 'Khách hàng')
;

insert into users (fullname, username, password, email, address, phone, avatar, activated, remember_token)
values ('Đào Huy Hoàng', 'hoangadmin', '$2a$10$oztyYOzexbKMwNQi.xfE4uOVjKByoNiuAHKO9zL83LMA0czAXtP3.',
        'hoang.admin@gmail.com',
        'Hồ Chí Minh', '0908983906', 'avatar1.png', true,
        'rAPHFeXDlQCjenQ6nffqe56hC9EulnyQTDKGzhuKjCIrVI4Cy0hWGEtsvJdA') -- password: 123456
     , ('Hoàng Đình Bảo Kỳ', 'kyuser', '$2a$10$oztyYOzexbKMwNQi.xfE4uOVjKByoNiuAHKO9zL83LMA0czAXtP3.',
        'kynguyen.user@gmail.com',
        'Hồ Chí Minh', '0985678910', 'avatar1.jpg', true,
        'sDh9x4HXrBCOJzgBH5qeZwcjVgN8Uv4u1WZBVQsYbp0moh7eDG260xJe07dF') -- password: 123456
     , ('Nguyễn Hữu Trí', 'tringuyenuser', '$2a$10$oztyYOzexbKMwNQi.xfE4uOVjKByoNiuAHKO9zL83LMA0czAXtP3.',
        'tringuyen.user@gmail.com',
        'Hồ Chí Minh', '0981234567', 'avatar1.jpg', true,
        '2iV7Lpa1sgCafdEOkbh2wVeYKamoc7kAb0CF6kAQJSVymts7g1uHZO9iUMI7') -- password: 123456
;



insert into users_roles (user_id, role_id)
values (1, 1)
     , (1, 2)
     , (1, 3)
     , (2, 2)
     , (3, 3)
;

# users:thông tin về khách hàng.
# airports:thông tin về các sân bay.
# flights:hông tin về các chuyến bay, liên kết với bảng airports thông qua các khóa ngoại departureAirportId và arrivalAirportId.
# bookings:thông tin về các đặt chỗ, liên kết với bảng customers và flights thông qua các khóa ngoại customerId và flightId.
# payments:thông tin về các khoản thanh toán, liên kết với bảng bookings thông qua khóa ngoại bookingId.
# seats:thông tin về chỗ ngồi trên các chuyến bay, liên kết với bảng flights thông qua khóa ngoại flightId.
# bookingDetails:thông tin chi tiết về các đặt chỗ, liên kết với bảng bookings và seats thông qua các khóa ngoại bookingId và seatId.


#admins: cập nhật thông tin chuyến bay
# customers:thông tin về khách hàng.
# airports:thông tin về các sân bay.
# flights:hông tin về các chuyến bay, liên kết với bảng airports thông qua các khóa ngoại departureAirportId và arrivalAirportId.
# bookings:thông tin về các đặt chỗ, liên kết với bảng customers và flights thông qua các khóa ngoại customerId và flightId.
# payments:thông tin về các khoản thanh toán, liên kết với bảng bookings thông qua khóa ngoại bookingId.
# seats:thông tin về chỗ ngồi trên các chuyến bay, liên kết với bảng flights thông qua khóa ngoại flightId.
# bookingDetails:thông tin chi tiết về các đặt chỗ, liên kết với bảng bookings và seats thông qua các khóa ngoại bookingId và seatId.