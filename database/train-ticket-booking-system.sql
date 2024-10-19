--create database TrainTicketBookingSystem
--go
--use TrainTicketBookingSystem

CREATE TABLE Employee (
    EmployeeID VARCHAR(50) PRIMARY KEY,  -- String in Java, VARCHAR in SQL
    FullName NVARCHAR(100) NOT NULL,     -- Supports Unicode characters (for names)
    Gender BIT NOT NULL,                 -- Boolean in Java, BIT in SQL (1 = Male, 0 = Female)
    DateOfBirth DATE NOT NULL,           -- LocalDate in Java, DATE in SQL
    Email VARCHAR(100) NOT NULL UNIQUE,  -- Email address with uniqueness constraint
    PhoneNumber VARCHAR(20) NULL,        -- Phone number is optional
    Role NVARCHAR(50) NOT NULL,          -- Role stored as a string
    StartingDate DATE NOT NULL,          -- Starting date of employment
    Salary DECIMAL(15, 2) NOT NULL,      -- Salary with 2 decimal precision
    ImageSource VARCHAR(255) NULL        -- Path to the image, optional
);

INSERT INTO Employee (EmployeeID, FullName, Gender, DateOfBirth, Email, PhoneNumber, Role, StartingDate, Salary, ImageSource) VALUES ('1', N'Pham Duc Tai', 1, '2003-10-27', 'phamductai102703', '0846107843', N'Manager', '2024-01-05', 1000, '/images/profile');

CREATE TABLE Account (
    Username NVARCHAR(50) NOT NULL PRIMARY KEY,    -- Username with uniqueness constraint
    Password NVARCHAR(255) NOT NULL,          -- Password (hashed for security in practice)
    EmployeeID VARCHAR(50) NOT NULL,          -- Foreign key referencing EmployeeID
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)  -- Foreign key constraint
);
go

CREATE   FUNCTION [dbo].[getEmployeeByAccount](@user VARCHAR(50))
RETURNS TABLE
AS
RETURN
SELECT e.*
FROM Employee e JOIN Account a 
ON e.EmployeeID = a.EmployeeID
WHERE a.Username = @user;
GOCREATE TABLE Train (
    TrainID INT PRIMARY KEY IDENTITY(1,1),       
    TrainNumber VARCHAR(50) NOT NULL,      
    Status NVARCHAR(50) NOT NULL              
);
GO

CREATE TABLE Coach (
    CoachID INT PRIMARY KEY IDENTITY(1,1),         
    CoachNumber INT NOT NULL,      
    CoachType NVARCHAR(50) NOT NULL,         
    Capacity INT NOT NULL,
    TrainID INT NOT NULL,              
    FOREIGN KEY (TrainID) REFERENCES Train(TrainID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);
GO

CREATE TABLE Seat (
    SeatID INT PRIMARY KEY IDENTITY(1, 1),          
    SeatNumber INT NOT NULL,          
    CoachID INT NOT NULL,            
    FOREIGN KEY (CoachID) REFERENCES Coach(CoachID)
        ON DELETE CASCADE
        ON UPDATE CASCADE
);


GO


INSERT INTO Train (TrainNumber, Status) VALUES ('SE1', 'Active')

INSERT INTO Coach (CoachNumber, CoachType, Capacity, TrainID) VALUES (1, N'Ngồi mềm đều hòa', 64, 1)
INSERT INTO Coach (CoachNumber, CoachType, Capacity, TrainID) VALUES (2, N'Ngồi mềm đều hòa', 64, 1)
INSERT INTO Coach (CoachNumber, CoachType, Capacity, TrainID) VALUES (3, N'Giường nằm khoang 6 điều hòa', 42, 1)
INSERT INTO Coach (CoachNumber, CoachType, Capacity, TrainID) VALUES (4, N'Giường nằm khoang 6 điều hòa', 42, 1)
INSERT INTO Coach (CoachNumber, CoachType, Capacity, TrainID) VALUES (5, N'Giường nằm khoang 4 điều hòa', 28, 1)
INSERT INTO Coach (CoachNumber, CoachType, Capacity, TrainID) VALUES (6, N'Giường nằm khoang 4 điều hòa', 28, 1)
INSERT INTO Coach (CoachNumber, CoachType, Capacity, TrainID) VALUES (7, N'Giường nằm khoang 4 điều hòa', 28, 1)
INSERT INTO Coach (CoachNumber, CoachType, Capacity, TrainID) VALUES (8, N'Giường nằm khoang 4 điều hòa', 28, 1)
INSERT INTO Coach (CoachNumber, CoachType, Capacity, TrainID) VALUES (9, N'Giường nằm khoang 4 điều hòa', 28, 1)
INSERT INTO Coach (CoachNumber, CoachType, Capacity, TrainID) VALUES (10, N'Giường nằm khoang 4 điều hòa', 28, 1)

select * from coach
go

INSERT INTO Seat (SeatNumber, CoachID) VALUES (1, 1), (2, 1), (3, 1), (5, 1), (6, 1), (7, 1), (8, 1), (9, 1), (10, 1), (11, 1), (12, 1), (13, 1), (14, 1), (15, 1), (16, 1), (17, 1), (18, 1), (19, 1), (20, 1), (21, 1), (22, 1), (23, 1), (24, 1), (25, 1), (26, 1), (27, 1), (28, 1), (29, 1), (30, 1), (31, 1), (32, 1), (33, 1), (34, 1), (35, 1), (36, 1), (37, 1), (38, 1), (39, 1), (40, 1), (41, 1), (42, 1), (43, 1), (44, 1), (45, 1), (46, 1), (47, 1), (48, 1), (49, 1), (50, 1), (51, 1), (52, 1), (53, 1), (54, 1), (55, 1), (56, 1), (57, 1), (58, 1), (59, 1), (60, 1), (61, 1), (62, 1), (63, 1), (64, 1);

INSERT INTO Seat (SeatNumber, CoachID) VALUES (1, 2), (2, 2), (3, 2), (5, 2), (6, 2), (7, 2), (8, 2), (9, 2), (10, 2), (11, 2), (12, 2), (13, 2), (14, 2), (15, 2), (16, 2), (17, 2), (18, 2), (19, 2), (20, 2), (21, 2), (22, 2), (23, 2), (24, 2), (25, 2), (26, 2), (27, 2), (28, 2), (29, 2), (30, 2), (31, 2), (32, 2), (33, 2), (34, 2), (35, 2), (36, 2), (37, 2), (38, 2), (39, 2), (40, 2), (41, 2), (42, 2), (43, 2), (44, 2), (45, 2), (46, 2), (47, 2), (48, 2), (49, 2), (50, 2), (51, 2), (52, 2), (53, 2), (54, 2), (55, 2), (56, 2), (57, 2), (58, 2), (59, 2), (60, 2), (61, 2), (62, 2), (63, 2), (64, 2);

INSERT INTO Seat (SeatNumber, CoachID) VALUES (1, 3), (2, 3), (3, 3), (4, 3), (5, 3), (6, 3), (7, 3), (8, 3), (9, 3), (10, 3), (11, 3), (12, 3), (13, 3), (14, 3), (15, 3), (16, 3), (17, 3), (18, 3), (19, 3), (20, 3), (21, 3), (22, 3), (23, 3), (24, 3), (25, 3), (26, 3), (27, 3), (28, 3), (29, 3), (30, 3), (31, 3), (32, 3), (33, 3), (34, 3), (35, 3), (36, 3), (37, 3), (38, 3), (39, 3), (40, 3), (41, 3), (42, 3);

INSERT INTO Seat (SeatNumber, CoachID) VALUES (1, 4), (2, 4), (3, 4), (4, 4), (5, 4), (6, 4), (7, 4), (8, 4), (9, 4), (10, 4), (11, 4), (12, 4), (13, 4), (14, 4), (15, 4), (16, 4), (17, 4), (18, 4), (19, 4), (20, 4), (21, 4), (22, 4), (23, 4), (24, 4), (25, 4), (26, 4), (27, 4), (28, 4), (29, 4), (30, 4), (31, 4), (32, 4), (33, 4), (34, 4), (35, 4), (36, 4), (37, 4), (38, 4), (39, 4), (40, 4), (41, 4), (42, 4);

INSERT INTO Seat (SeatNumber, CoachID) VALUES (1, 5), (2, 5), (3, 5), (4, 5), (5, 5), (6, 5), (7, 5), (8, 5), (9, 5), (10, 5), (11, 5), (12, 5), (13, 5), (14, 5), (15, 5), (16, 5), (17, 5), (18, 5), (19, 5), (20, 5), (21, 5), (22, 5), (23, 5), (24, 5), (25, 5), (26, 5), (27, 5), (28, 5);

INSERT INTO Seat (SeatNumber, CoachID) VALUES (1, 6), (2, 6), (3, 6), (4, 6), (5, 6), (6, 6), (7, 6), (8, 6), (9, 6), (10, 6), (11, 6), (12, 6), (13, 6), (14, 6), (15, 6), (16, 6), (17, 6), (18, 6), (19, 6), (20, 6), (21, 6), (22, 6), (23, 6), (24, 6), (25, 6), (26, 6), (27, 6), (28, 6);

INSERT INTO Seat (SeatNumber, CoachID) VALUES (1, 7), (2, 7), (3, 7), (4, 7), (5, 7), (6, 7), (7, 7), (8, 7), (9, 7), (10, 7), (11, 7), (12, 7), (13, 7), (14, 7), (15, 7), (16, 7), (17, 7), (18, 7), (19, 7), (20, 7), (21, 7), (22, 7), (23, 7), (24, 7), (25, 7), (26, 7), (27, 7), (28, 7);

INSERT INTO Seat (SeatNumber, CoachID) VALUES (1, 8), (2, 8), (3, 8), (4, 8), (5, 8), (6, 8), (7, 8), (8, 8), (9, 8), (10, 8), (11, 8), (12, 8), (13, 8), (14, 8), (15, 8), (16, 8), (17, 8), (18, 8), (19, 8), (20, 8), (21, 8), (22, 8), (23, 8), (24, 8), (25, 8), (26, 8), (27, 8), (28, 8);

INSERT INTO Seat (SeatNumber, CoachID) VALUES (1, 9), (2, 9), (3, 9), (4, 9), (5, 9), (6, 9), (7, 9), (8, 9), (9, 9), (10, 9), (11, 9), (12, 9), (13, 9), (14, 9), (15, 9), (16, 9), (17, 9), (18, 9), (19, 9), (20, 9), (21, 9), (22, 9), (23, 9), (24, 9), (25, 9), (26, 9), (27, 9), (28, 9);

INSERT INTO Seat (SeatNumber, CoachID) VALUES (1, 10), (2, 10), (3, 10), (4, 10), (5, 10), (6, 10), (7, 10), (8, 10), (9, 10), (10, 10), (11, 10), (12, 10), (13, 10), (14, 10), (15, 10), (16, 10), (17, 10), (18, 10), (19, 10), (20, 10), (21, 10), (22, 10), (23, 10), (24, 10), (25, 10), (26, 10), (27, 10), (28, 10);

go

SELECT 
    t.TrainID, 
    t.TrainNumber, 
    COUNT(c.CoachID) AS NumberOfCoaches, 
    SUM(c.Capacity) AS TotalCapacity, 
    (SELECT COUNT(DISTINCT CoachType) FROM Coach) AS NumberOfCoachTypes,
    t.Status
FROM 
    Train t 
JOIN 
    Coach c ON t.TrainID = c.TrainID 
GROUP BY 
    t.TrainID, 
    t.TrainNumber, 
    t.Status;

go

CREATE FUNCTION dbo.GetAllTrainDetails()
RETURNS TABLE
AS
RETURN
(
    SELECT 
        t.TrainID,
        t.TrainNumber,
        COUNT(c.CoachID) AS NumberOfCoaches,
        SUM(c.Capacity) AS Capacity,
        COUNT(DISTINCT c.CoachType) AS NumberOfCoachTypes,
        STUFF((
            SELECT DISTINCT ', ' + c2.CoachType
            FROM Coach c2
            WHERE c2.TrainID = t.TrainID
            FOR XML PATH('')), 1, 2, '') AS CoachTypes,
        t.Status
    FROM 
        Train t
    LEFT JOIN 
        Coach c ON t.TrainID = c.TrainID
    GROUP BY 
        t.TrainID, t.TrainNumber, t.Status
);
go

select * from train

select * from train where TrainNumber LIKE '%%'
select * from coach

SELECT TrainID, TrainNumber, Status FROM Train WHERE TrainID = 3

SELECT COUNT(c.CoachID) AS NumberOfCoaches FROM Train t LEFT JOIN Coach c ON t.TrainID = c.TrainID WHERE T.TrainID = 3 GROUP BY t.TrainID, t.TrainNumber 

SELECT * FROM Coach WHERE TrainID = 3

CREATE TABLE Line (
    lineID INT IDENTITY(1,1) PRIMARY KEY,
    lineName NVARCHAR(255) NOT NULL
);

CREATE TABLE TrainJourney (
    trainJourneyID INT IDENTITY(1,1) PRIMARY KEY,
    trainJourneyName NVARCHAR(255) NOT NULL,
    trainID INT NOT NULL,
	lineID INT,
    basePrice DECIMAL(10, 2) NOT NULL,
	CONSTRAINT FK_Line FOREIGN KEY (lineID) REFERENCES Line(lineID),
    CONSTRAINT FK_Train FOREIGN KEY (trainID) REFERENCES Train(trainID)
);

CREATE TABLE Station (
    stationID INT IDENTITY(1,1) PRIMARY KEY,
    stationName NVARCHAR(255) NOT NULL
);

CREATE TABLE LineStop (
    lineID INT NOT NULL, 
    stationID INT NOT NULL,
    stopOrder INT NOT NULL, 
    distance INT NOT NULL,

    PRIMARY KEY (lineID, stationID),

    CONSTRAINT FK_LineStop FOREIGN KEY (lineID) REFERENCES Line(lineID) 
        ON DELETE CASCADE ON UPDATE CASCADE,
    CONSTRAINT FK_Station FOREIGN KEY (stationID) REFERENCES Station(stationID) 
        ON DELETE CASCADE ON UPDATE CASCADE
);

CREATE TABLE Stop (
    stopID INT IDENTITY(1,1) PRIMARY KEY,  -- Auto-increment primary key
    trainJourneyID INT NOT NULL,           -- Foreign key to TrainJourney table
    stationID INT NOT NULL,                -- Foreign key to Station table
    stopOrder INT NOT NULL,
    distance INT NOT NULL,
    departureDate DATE NOT NULL,
    arrivalTime TIME NOT NULL,
    departureTime TIME NOT NULL,
    CONSTRAINT FK_TrainJourney FOREIGN KEY (trainJourneyID) REFERENCES TrainJourney(trainJourneyID),
    CONSTRAINT FK_StopStation FOREIGN KEY (stationID) REFERENCES Station(stationID)
);

CREATE TABLE Passenger (
    passengerID INT IDENTITY(1,1) PRIMARY KEY,
    fullName NVARCHAR(255) NOT NULL, 
    passengerType NVARCHAR(50) NOT NULL,
    identificationNumber NVARCHAR(50) NOT NULL	
);

CREATE TABLE Customer (
    customerID INT IDENTITY(1,1) PRIMARY KEY,
    fullName NVARCHAR(255) NOT NULL,
    phoneNumber NVARCHAR(15) NOT NULL, 
    email NVARCHAR(255) NOT NULL UNIQUE,
    identificationNumber NVARCHAR(50) NOT NULL
);

CREATE TABLE [Order] (
    orderID INT IDENTITY(1,1) PRIMARY KEY,
    orderDate DATETIME NOT NULL,
    note NVARCHAR(255),
    paymentMethod NVARCHAR(50) NOT NULL, 
    customerID INT NOT NULL,
    trainJourneyID INT NOT NULL,
    employeeID VARCHAR(50) NOT NULL,
    FOREIGN KEY (customerID) REFERENCES Customer(customerID),
    FOREIGN KEY (trainJourneyID) REFERENCES TrainJourney(trainJourneyID),
    FOREIGN KEY (employeeID) REFERENCES Employee(employeeID)
);

CREATE TABLE Ticket (
    ticketID INT IDENTITY(1,1) PRIMARY KEY, -- Auto-incremented key
    trainJourneyID INT NOT NULL,  -- Foreign key reference to TrainJourney
    seatID INT NOT NULL,  -- Foreign key reference to Seat
    passengerID INT NOT NULL,  -- Foreign key reference to Passenger
    orderID INT NOT NULL,  -- Foreign key reference to Order
    FOREIGN KEY (trainJourneyID) REFERENCES TrainJourney(trainJourneyID),
    FOREIGN KEY (seatID) REFERENCES Seat(seatID),
    FOREIGN KEY (passengerID) REFERENCES Passenger(passengerID),
    FOREIGN KEY (orderID) REFERENCES [Order](orderID)
);

CREATE TABLE TicketDetail (
    stopID INT NOT NULL,  -- Foreign key reference to Stop
    ticketID INT NOT NULL,  -- Foreign key reference to Ticket
    PRIMARY KEY (stopID, ticketID),  -- Composite primary key
    FOREIGN KEY (stopID) REFERENCES Stop(stopID),
    FOREIGN KEY (ticketID) REFERENCES Ticket(ticketID)
);

INSERT INTO Line (lineName) VALUES (N'Đà Lạt - Trại Mát')
INSERT INTO TrainJourney (trainJourneyName, trainID, lineID, basePrice) VALUES (N'Đà lạt - Trại Mát 9:55am', 1, 1, 2000);
INSERT INTO Station (stationName) VALUES (N'Đà Lạt')
INSERT INTO Station (stationName) VALUES (N'Trại Mát')
INSERT INTO LineStop (lineID, stationID, stopOrder, distance) VALUES (1, 1, 1, 0)
INSERT INTO LineStop (lineID, stationID, stopOrder, distance) VALUES (1, 2, 2, 7)
INSERT INTO Stop (trainJourneyID, stationID, stopOrder, distance, departureDate, arrivalTime, departureTime) VALUES (1, 1, 1, 0, '2024-10-16', '09:55:00', '09:55:00')
INSERT INTO Stop (trainJourneyID, stationID, stopOrder, distance, departureDate, arrivalTime, departureTime) VALUES (1, 2, 2, 7, '2024-10-16', '10:25:00', '10:25:00')
INSERT INTO Passenger (fullName, passengerType, identificationNumber) VALUES (N'Phạm Đức Tài', N'Người lớn', '080203001008')
INSERT INTO Customer (fullName, phoneNumber, email, identificationNumber) VALUES (N'Phạm Đức Tài', '0846107843', 'phamductai10272003@gmail.com', '080203001008')
INSERT INTO [Order] (orderDate, note, paymentMethod, customerID, trainJourneyID, employeeID) VALUES (GETDATE(), N'Đây là một ghi chú', N'Tiền mặt', 1, 1, '1')
INSERT INTO Ticket (trainJourneyID, seatID, passengerID, orderID) VALUES (1, 1, 1, 1)
INSERT INTO TicketDetail (stopID, ticketID) VALUES (1, 1)
INSERT INTO TicketDetail (stopID, ticketID) VALUES (2, 1)

INSERT INTO Line (lineName) VALUES (N'Sài Gòn - Nha Trang')

INSERT INTO Station (stationName) VALUES (N'Sài Gòn')
INSERT INTO Station (stationName) VALUES (N'Dĩ An')
INSERT INTO Station (stationName) VALUES (N'Biên Hòa')
INSERT INTO Station (stationName) VALUES (N'Tháp Chàm')
INSERT INTO Station (stationName) VALUES (N'Nha Trang')

select * from station

INSERT INTO LineStop (lineID, stationID, stopOrder, distance) VALUES (2, 3, 1, 0)
INSERT INTO LineStop (lineID, stationID, stopOrder, distance) VALUES (2, 4, 2, 19)
INSERT INTO LineStop (lineID, stationID, stopOrder, distance) VALUES (2, 5, 3, 29)
INSERT INTO LineStop (lineID, stationID, stopOrder, distance) VALUES (2, 6, 4, 318)
INSERT INTO LineStop (lineID, stationID, stopOrder, distance) VALUES (2, 7, 5, 411)

select stopOrder, s.stationID, s.stationName, distance from line l join LineStop ls on l.lineID = ls.lineID join station s on ls.stationID = s.stationID where l.lineID = 1

SELECT 
    tj.trainJourneyID,
    tj.trainID,
    CONCAT(s1.stationName, ' - ', s2.stationName) AS Journey,
    CONCAT(st1.departureTime, ' - ', st2.arrivalTime) AS Time,
    SUM(st1.distance) AS Distance,
    COUNT(t.ticketID) / SUM(c.Capacity) AS Book
FROM 
    TrainJourney tj
JOIN 
    Stop st1 ON tj.trainJourneyID = st1.trainJourneyID
JOIN 
    Stop st2 ON tj.trainJourneyID = st2.trainJourneyID AND st2.stopOrder = (
        SELECT MAX(stopOrder) FROM Stop WHERE trainJourneyID = tj.trainJourneyID
    )
JOIN 
    Station s1 ON st1.stationID = s1.stationID
JOIN 
    Station s2 ON st2.stationID = s2.stationID
JOIN 
    Ticket t ON tj.trainJourneyID = t.trainJourneyID
JOIN 
    Seat se ON t.seatID = se.SeatID
JOIN 
    Coach c ON se.CoachID = c.CoachID
GROUP BY 
    tj.trainJourneyID, tj.trainID, s1.stationName, s2.stationName, st1.departureTime, st2.arrivalTime;

-- trainJourneyID, trainJourneyName, TrainNumber
SELECT trainJourneyID, trainJourneyName, TrainNumber
FROM TrainJourney tj
JOIN Train t ON tj.trainID = t.TrainID

-- depatureStation and departureTime

select * from trainjourney
SELECT 
    st.stationName,
    sp.departureTime
FROM 
    TrainJourney tj
JOIN 
    Stop sp ON tj.trainJourneyID = sp.trainJourneyID
JOIN 
    Station st ON sp.stationID = st.stationID
WHERE tj.trainJourneyID = 1 AND stopOrder = 1

-- arrivalStaion and arrivalTime
SELECT 
    st.stationName AS arrivalStation,
    sp.arrivalTime AS arrivalTime,
	distance
FROM 
    TrainJourney tj
JOIN 
    Stop sp ON tj.trainJourneyID = sp.trainJourneyID
JOIN 
    Station st ON sp.stationID = st.stationID
WHERE 
    tj.trainJourneyID = 1 
    AND sp.stopOrder = (
        SELECT MAX(stopOrder) 
        FROM Stop 
        WHERE trainJourneyID = tj.trainJourneyID
    );

-- number of seats
select count(SeatNumber) as NumberOfSeats
from train t join coach c on t.TrainID = c.TrainID join seat s on c.CoachID = s.CoachID
where t.TrainID = 7

select count(ticketID) as BookedTicket
from Ticket
where trainJourneyID = 1
--------------------------------------
--------------------------------------
--------------------------------------
--------------------------------------

go
select * from dbo.fn_GetAllTrainJourneyDetails()
CREATE FUNCTION dbo.fn_GetAllTrainJourneyDetails()
RETURNS TABLE
AS
RETURN
(
    SELECT 
        tj.trainJourneyID,
        tj.trainJourneyName,
        t.TrainNumber,
        
        -- Departure station and time
        (SELECT 
            st1.stationName
         FROM 
            Stop sp1
         JOIN 
            Station st1 ON sp1.stationID = st1.stationID
         WHERE 
            sp1.trainJourneyID = tj.trainJourneyID 
            AND sp1.stopOrder = 1) AS departureStation,
        
        (SELECT 
            sp1.departureTime
         FROM 
            Stop sp1
         WHERE 
            sp1.trainJourneyID = tj.trainJourneyID 
            AND sp1.stopOrder = 1) AS departureTime,

		(SELECT 
            sp1.departureDate
         FROM 
            Stop sp1
         WHERE 
            sp1.trainJourneyID = tj.trainJourneyID 
            AND sp1.stopOrder = 1) AS departureDate,
        
        -- Arrival station and time
        (SELECT 
            st2.stationName
         FROM 
            Stop sp2
         JOIN 
            Station st2 ON sp2.stationID = st2.stationID
         WHERE 
            sp2.trainJourneyID = tj.trainJourneyID 
            AND sp2.stopOrder = (
                SELECT MAX(stopOrder) 
                FROM Stop 
                WHERE trainJourneyID = tj.trainJourneyID
            )) AS arrivalStation,
        
        (SELECT 
            sp2.arrivalTime
         FROM 
            Stop sp2
         WHERE 
            sp2.trainJourneyID = tj.trainJourneyID 
            AND sp2.stopOrder = (
                SELECT MAX(stopOrder) 
                FROM Stop 
                WHERE trainJourneyID = tj.trainJourneyID
            )) AS arrivalTime,
            
        -- Total distance of journey
        (SELECT 
            SUM(distance)
         FROM 
            Stop
         WHERE 
            trainJourneyID = tj.trainJourneyID) AS totalDistance,

        -- Total number of seats in the train
        (SELECT 
            COUNT(s.SeatNumber)
         FROM 
            Train t1
         JOIN 
            Coach c ON t1.TrainID = c.TrainID
         JOIN 
            Seat s ON c.CoachID = s.CoachID
         WHERE 
            t1.TrainID = t.TrainID) AS totalSeats,

        -- Booked tickets for the journey
        (SELECT 
            COUNT(ticketID)
         FROM 
            Ticket
         WHERE 
            trainJourneyID = tj.trainJourneyID) AS bookedTickets

    FROM 
        TrainJourney tj
    JOIN 
        Train t ON tj.trainID = t.TrainID
);
go

SELECT trainJourneyID, trainJourneyName, TrainNumber, departureStation, arrivalStation, departureTime, arrivalTime, totalDistance, bookedTickets, totalSeats FROM dbo.fn_GetAllTrainJourneyDetails();

select * from TrainJourney

select * from Stop

SELECT trainJourneyName, t.trainID, t.trainNumber, t.Status, l.lineID, l.lineName, basePrice FROM TrainJourney tj join Train t on tj.trainID = t.TrainID join line l on tj.lineID = l.lineID WHERE trainJourneyID = 1

select stopID, trainJourneyID, station.stationID, station.stationName, stopOrder, distance, departureDate, arrivalTime, departureTime from stop join Station on stop.stationID = Station.stationID where trainJourneyID = 8

update TrainJourney set trainJourneyName = 'Something else', basePrice = 14 where trainJourneyID = 8

select * from stop

UPDATE Stop SET departureDate = '2024-10-16', arrivalTime = '11:30:00', departureTime = '11:30:00' WHERE stopID = 17;


select * from trainjourney
select * from station

SELECT 
    tj.trainJourneyID, 
    tj.trainJourneyName, 
    tj.trainID, 
    tj.basePrice, 
    ds.stationID AS departureStationID, 
    ds.departureDate AS departureDate, 
    ds.departureTime AS departureTime, 
    arrival_stop.stationID AS arrivalStationID, 
    arrival_stop.arrivalTime AS arrivalTime
FROM 
    TrainJourney tj
JOIN 
    Stop ds ON tj.trainJourneyID = ds.trainJourneyID  -- Departure stop
JOIN 
    Stop arrival_stop ON tj.trainJourneyID = arrival_stop.trainJourneyID  -- Arrival stop
WHERE 
    ds.stationID = 3  -- Parameter for the departure station ID
    AND arrival_stop.stationID = 6  -- Parameter for the arrival station ID
    AND ds.departureDate = '2024-10-16'  -- Parameter for the departure date
    AND ds.stopOrder < arrival_stop.stopOrder  -- Ensure departure stop is before arrival stop
ORDER BY 
    ds.departureTime;

go
CREATE FUNCTION dbo.GetTrainJourneysByStationNames
(
    @departureStationName NVARCHAR(255),
    @arrivalStationName NVARCHAR(255),
    @departureDate DATE
)
RETURNS TABLE
AS
RETURN
(
    SELECT 
        tj.trainJourneyID,
		t.TrainID,
		t.TrainNumber,
        tj.trainJourneyName,
        ds.stationID AS departureStationID,
        ds.departureDate,
        arrival_stop.stationID AS arrivalStationID,
        arrival_stop.arrivalTime
    FROM 
        TrainJourney tj
	JOIN 
		Train t ON  tj.trainID = t.TrainID
    JOIN 
        Stop ds ON tj.trainJourneyID = ds.trainJourneyID  -- Departure stop
    JOIN 
        Stop arrival_stop ON tj.trainJourneyID = arrival_stop.trainJourneyID  -- Arrival stop
    JOIN
        Station dep_station ON ds.stationID = dep_station.stationID
    JOIN
        Station arr_station ON arrival_stop.stationID = arr_station.stationID
    WHERE 
        dep_station.stationName = @departureStationName
        AND arr_station.stationName = @arrivalStationName
        AND ds.departureDate = @departureDate
        AND ds.stopOrder < arrival_stop.stopOrder  -- Ensure departure stop is before arrival stop
    -- Using TOP 1000 to allow ORDER BY
    ORDER BY 
        ds.departureTime
    OFFSET 0 ROWS
);
go

SELECT * FROM dbo.GetTrainJourneysByStationNames(N'Biên Hòa', N'Tháp Chàm', '2024-10-17');

select * from stop join station on stop.stationID = station.stationID where trainJourneyID = 9 

-------------------------------------------------------------------
-------------------------------------------------------------------
-------------------------------------------------------------------
-------------------------------------------------------------------
-------------------------------------------------------------------
-------------------------------------------------------------------
-------------------------------------------------------------------
-------------------------------------------------------------------
-------------------------------------------------------------------
-------------------------------------------------------------------
-------------------- create new function 16/10---------------------

CREATE FUNCTION dbo.GetTrainJourneysByStationNames
(
    @departureStationName NVARCHAR(255),
    @arrivalStationName NVARCHAR(255),
    @departureDate DATE
)
RETURNS TABLE
AS
RETURN
(
    SELECT 
        tj.trainJourneyID,
        tj.trainJourneyName,
        t.TrainID,
        t.TrainNumber,
        ds.stationID AS departureStationID,
        dep_station.stationName AS departureStationName,  -- Added departure station name
        ds.departureDate,
        ds.departureTime,
        arrival_stop.stationID AS arrivalStationID,
        arr_station.stationName AS arrivalStationName,  -- Added arrival station name
        arrival_stop.departureDate AS arrivalDate,
        arrival_stop.arrivalTime,
        -- Calculate journey duration in minutes
        DATEDIFF(MINUTE, 
                 CAST(ds.departureDate AS DATETIME) + CAST(ds.departureTime AS DATETIME),  -- Departure date and time
                 CAST(arrival_stop.departureDate AS DATETIME) + CAST(arrival_stop.arrivalTime AS DATETIME)  -- Arrival date and time
        ) AS journeyDuration,  -- Duration in minutes

        -- Calculate available seats by subtracting booked seats from total seats
        (SELECT COUNT(*) 
         FROM Seat s
         JOIN Coach c ON s.CoachID = c.CoachID
         WHERE c.TrainID = tj.trainID
        ) - 
        (SELECT COUNT(*) 
         FROM Ticket t
         JOIN TicketDetail td ON t.ticketID = td.ticketID
         WHERE t.trainJourneyID = tj.trainJourneyID
           AND td.stopID BETWEEN ds.stopID AND arrival_stop.stopID  -- Ensure tickets are for this leg of the journey
        ) AS numberOfAvailableSeatsLeft  -- Available seats
    FROM 
        TrainJourney tj
    JOIN 
        Train t ON t.TrainID = tj.trainID
    JOIN 
        Stop ds ON tj.trainJourneyID = ds.trainJourneyID  -- Departure stop
    JOIN 
        Stop arrival_stop ON tj.trainJourneyID = arrival_stop.trainJourneyID  -- Arrival stop
    JOIN
        Station dep_station ON ds.stationID = dep_station.stationID  -- Departure station
    JOIN
        Station arr_station ON arrival_stop.stationID = arr_station.stationID  -- Arrival station
    WHERE 
        dep_station.stationName = @departureStationName
        AND arr_station.stationName = @arrivalStationName
        AND ds.departureDate = @departureDate
        AND ds.stopOrder < arrival_stop.stopOrder  -- Ensure departure stop is before arrival stop
    -- Using TOP 1000 to allow ORDER BY
    ORDER BY 
        ds.departureTime
    OFFSET 0 ROWS
);



CREATE FUNCTION dbo.GetDistanceBetweenStops(
    @trainJourneyID INT,
    @stationID1 INT,
    @stationID2 INT
) 
RETURNS DECIMAL(10, 2) 
AS
BEGIN
    DECLARE @distance DECIMAL(10, 2);

    SELECT 
        @distance = ABS(s1.distance - s2.distance)
    FROM 
        Stop s1
    JOIN 
        Stop s2 
        ON s1.trainJourneyID = s2.trainJourneyID
    WHERE 
        s1.trainJourneyID = @trainJourneyID
        AND s1.stationID = @stationID1
        AND s2.stationID = @stationID2;

    RETURN @distance;
END;

CREATE TABLE Service (
    serviceID INT IDENTITY(1,1) PRIMARY KEY,  -- Auto-incrementing primary key starting from 1
    serviceName NVARCHAR(100) NOT NULL,        -- Adjust length as needed
    price DECIMAL(10, 2) NOT NULL,             -- Store prices with two decimal places
    type NVARCHAR(50) NOT NULL,                -- Adjust length based on expected 
    imageSource NVARCHAR(255),                  -- To store the URL or path of the image
	quantity int
);

CREATE TABLE ServiceDetail (
    serviceID INT NOT NULL,                        -- Foreign key to Service table
    orderID INT NOT NULL,                          -- Foreign key to Order table
    quantity INT NOT NULL CHECK (quantity > 0),    -- Quantity must be positive

    CONSTRAINT PK_ServiceDetail PRIMARY KEY (serviceID, orderID),  -- Composite primary key
    CONSTRAINT FK_Service FOREIGN KEY (serviceID) REFERENCES Service(serviceID) 
        ON DELETE CASCADE,  -- Optional: cascade deletion
    CONSTRAINT FK_Order FOREIGN KEY (orderID) REFERENCES [Order](orderID) 
        ON DELETE CASCADE   -- Optional: cascade deletion
);

INSERT INTO Service (serviceName, price, type, imageSource, quantity) 
VALUES 
    ('Bánh Mì Thịt', 40000, N'đồ ăn', 'images/test_image.png', 100),
    ('Trà Sửa Trân Châu', 40000, N'nước uống', 'images/test_image.png', 100),
    ('Phở Bò', 80000, N'đồ ăn', 'images/test_image.png', 100),
    ('Cà Phê Sửa Đá', 40000, N'nước uống', 'images/test_image.png', 100);
-----------------------------------------------
CREATE FUNCTION GetStopsForJourney (
    @trainJourneyID INT,
    @departureStationID INT,
    @arrivalStationID INT
)
RETURNS TABLE
AS
RETURN 
(
    SELECT 
        s1.stopID, 
        s1.trainJourneyID, 
        s1.stationID, 
        st.stationName, 
        s1.stopOrder, 
        s1.distance, 
        s1.departureDate, 
        s1.arrivalTime, 
        s1.departureTime
    FROM 
        Stop s1
    JOIN 
        Station st ON s1.stationID = st.stationID
    JOIN 
        Stop s2 ON s1.trainJourneyID = s2.trainJourneyID
    JOIN 
        Stop s3 ON s1.trainJourneyID = s3.trainJourneyID
    WHERE 
        s1.trainJourneyID = @trainJourneyID
        AND s2.stationID = @departureStationID 
        AND s3.stationID = @arrivalStationID 
        AND s1.stopOrder BETWEEN s2.stopOrder AND s3.stopOrder
);
go

----------------------------------------
CREATE FUNCTION dbo.fn_GetUnavailableSeats (
    @trainJourneyID INT,
    @departureStationID INT,
    @arrivalStationID INT
)
RETURNS TABLE
AS
RETURN
WITH BookedSeats AS (
    SELECT 
        t.seatID, 
        MIN(stop1.stopOrder) AS bookedStartOrder,
        MAX(stop2.stopOrder) AS bookedEndOrder
    FROM 
        Ticket t
    JOIN TicketDetail td1 ON t.ticketID = td1.ticketID
    JOIN Stop stop1 ON td1.stopID = stop1.stopID  
    JOIN TicketDetail td2 ON t.ticketID = td2.ticketID
    JOIN Stop stop2 ON td2.stopID = stop2.stopID  
    WHERE 
        t.trainJourneyID = @trainJourneyID
    GROUP BY 
        t.seatID
)
SELECT 
    s.SeatID, 
    s.SeatNumber, 
    c.CoachNumber, 
    c.CoachType
FROM 
    Seat s
JOIN Coach c ON s.CoachID = c.CoachID
JOIN BookedSeats bs ON s.SeatID = bs.seatID
JOIN Stop depStop ON depStop.stationID = @departureStationID 
                  AND depStop.trainJourneyID = @trainJourneyID
JOIN Stop arrStop ON arrStop.stationID = @arrivalStationID 
                  AND arrStop.trainJourneyID = @trainJourneyID
WHERE 
    depStop.stopOrder < arrStop.stopOrder  -- Ensure correct journey direction
    AND (
        depStop.stopOrder BETWEEN bs.bookedStartOrder AND bs.bookedEndOrder
        OR arrStop.stopOrder BETWEEN bs.bookedStartOrder AND bs.bookedEndOrder
        OR bs.bookedStartOrder BETWEEN depStop.stopOrder AND arrStop.stopOrder
    )
go