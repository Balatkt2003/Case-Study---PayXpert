-- Employee Table
use  payx;

CREATE TABLE Employee (
    EmployeeID INT PRIMARY KEY AUTO_INCREMENT,
    FirstName VARCHAR(50) NOT NULL,
    LastName VARCHAR(50) NOT NULL,
    DateOfBirth DATE NOT NULL,
    Gender ENUM('Male', 'Female', 'Other') NOT NULL,
    Email VARCHAR(100) UNIQUE NOT NULL,
    PhoneNumber VARCHAR(15) UNIQUE NOT NULL,
    Address TEXT NOT NULL,
    Position VARCHAR(50) NOT NULL,
    JoiningDate DATE NOT NULL
);

-- Payroll Table
CREATE TABLE Payroll (
    PayrollID INT PRIMARY KEY,
    EmployeeID INT,
    PayPeriodStartDate DATE,
    PayPeriodEndDate DATE,
    BasicSalary DECIMAL(10, 2),
    OvertimePay DECIMAL(10, 2),
    Deductions DECIMAL(10, 2),
    NetSalary DECIMAL(10, 2),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);

-- Tax Table
CREATE TABLE Tax (
    TaxID INT PRIMARY KEY,
    EmployeeID INT,
    TaxYear YEAR,
    TaxableIncome DECIMAL(10, 2),
    TaxAmount DECIMAL(10, 2),
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID)
);


-- FinancialRecord Table
CREATE TABLE FinancialRecord (
    RecordID INT PRIMARY KEY AUTO_INCREMENT,
    EmployeeID INT NOT NULL,
    RecordDate DATE NOT NULL,
    Description TEXT NOT NULL,
    Amount DECIMAL(10,2) NOT NULL,
    RecordType ENUM('Income', 'Expense', 'Tax Payment') NOT NULL,
    FOREIGN KEY (EmployeeID) REFERENCES Employee(EmployeeID) ON DELETE CASCADE
);

--  Employees
INSERT INTO Employee (FirstName, LastName, DateOfBirth, Gender, Email, PhoneNumber, Address, Position, JoiningDate)
VALUES 
('Rahul', 'Sharma', '1990-05-15', 'Male', 'rahul.sharma@gmail.com', '9876543210', '12 MG Road, New Delhi', 'Software Engineer', '2021-07-01'),
('Priya', 'Menon', '1988-08-22', 'Female', 'priya.menon@gmail.com', '9123456789', '34 Brigade Road, Bengaluru', 'HR Manager', '2019-03-15'),
('Amit', 'Patel', '1992-11-10', 'Male', 'amit.patel@gmail.com', '8901234567', '76 CG Road, Ahmedabad', 'Sales Executive', '2020-05-20'),
('Anjali', 'Reddy', '1995-03-25', 'Female', 'anjali.reddy@gmail.com', '8765432109', '221 Banjara Hills, Hyderabad', 'Marketing Analyst', '2022-01-10'),
('Karan', 'Verma', '1993-07-18', 'Male', 'karan.verma@gmail.com', '7654321098', '98 Park Street, Kolkata', 'Product Manager', '2018-09-30'),
('Neha', 'Singh', '1991-12-05', 'Female', 'neha.singh@gmail.com', '6543210987', '45 Koregaon Park, Pune', 'Data Scientist', '2020-12-01'),
('Rohit', 'Nair', '1994-09-14', 'Male', 'rohit.nair@gmail.com', '9432109876', '67 Panampilly Nagar, Kochi', 'Software Engineer', '2021-08-20'),
('Sanya', 'Kapoor', '1989-04-08', 'Female', 'sanya.kapoor@gmail.com', '9321098765', '88 Cuffe Parade, Mumbai', 'HR Executive', '2017-06-25'),
('Arjun', 'Mishra', '1990-06-30', 'Male', 'arjun.mishra@gmail.com', '9210987654', '109 Gomti Nagar, Lucknow', 'Finance Manager', '2016-11-10'),
('Isha', 'Desai', '1996-02-12', 'Female', 'isha.desai@gmail.com', '8109876543', '154 RS Puram, Coimbatore', 'Customer Support', '2023-03-05');


--  Payroll Records
INSERT INTO Payroll (PayrollID, EmployeeID, PayPeriodStartDate, PayPeriodEndDate, BasicSalary, OvertimePay, Deductions, NetSalary)
VALUES
(1, 1, '2025-01-01', '2025-01-31', 50000.00, 5000.00, 7000.00, 48000.00),
(2, 2, '2025-01-15', '2025-02-14', 60000.00, 4000.00, 8000.00, 56000.00),
(3, 3, '2025-02-01', '2025-02-28', 70000.00, 7000.00, 15000.00, 62000.00),
(4, 4, '2025-02-15', '2025-03-15', 40000.00, 2000.00, 3000.00, 39000.00),
(5, 5, '2025-03-01', '2025-03-31', 55000.00, 3000.00, 6000.00, 52000.00),
(6, 6, '2025-03-15', '2025-04-14', 45000.00, 2500.00, 5000.00, 42500.00),
(7, 7, '2025-04-01', '2025-04-30', 52000.00, 3500.00, 4000.00, 51500.00),
(8, 8, '2025-04-15', '2025-05-14', 48000.00, 1500.00, 3500.00, 46000.00),
(9, 9, '2025-05-01', '2025-05-31', 62000.00, 6000.00, 9000.00, 59000.00),
(10,10, '2025-05-15', '2025-06-14', 58000.00, 5000.00, 7500.00, 55500.00);

--  Tax Records
INSERT INTO Tax (TaxID, EmployeeID, TaxYear, TaxableIncome, TaxAmount)
VALUES
(1, 1, 2024, 480000.00, 48000.00),
(2, 2, 2024, 560000.00, 56000.00),
(3, 3, 2024, 620000.00, 62000.00),
(4, 4, 2024, 390000.00, 39000.00),
(5, 5, 2024, 520000.00, 52000.00),
(6, 6, 2024, 425000.00, 42500.00),
(7, 7, 2024, 515000.00, 51500.00),
(8, 8, 2024, 460000.00, 46000.00),
(9, 9, 2024, 590000.00, 59000.00),
(10,10, 2024, 555000.00, 55500.00);


--  Financial Records
INSERT INTO FinancialRecord (EmployeeID, RecordDate, Description, Amount, RecordType)
VALUES
(1, '2024-03-15', 'Bonus Payment', 500.00, 'Income'),
(2, '2024-03-16', 'Business Travel Expense', 200.00, 'Expense'),
(3, '2024-03-17', 'Salary Advance Deduction', 300.00, 'Expense'),
(4, '2024-03-18', 'Tax Payment', 450.00, 'Tax Payment'),
(5, '2024-03-19', 'Performance Bonus', 600.00, 'Income'),
(6, '2024-03-20', 'Training Expense', 250.00, 'Expense'),
(7, '2024-03-21', 'Medical Reimbursement', 400.00, 'Income'),
(8, '2024-03-22', 'Office Supplies Expense', 150.00, 'Expense'),
(9, '2024-03-23', 'Client Lunch Reimbursement', 180.00, 'Income'),
(10, '2024-03-24', 'Tax Deduction', 350.00, 'Tax Payment');

select * from financialrecord