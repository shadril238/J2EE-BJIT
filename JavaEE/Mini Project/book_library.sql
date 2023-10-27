-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 26, 2023 at 12:59 PM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.0.28

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `book_library`
--

-- --------------------------------------------------------

--
-- Table structure for table `books`
--

CREATE TABLE `books` (
  `is_active` bit(1) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `author` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `img_url` varchar(255) DEFAULT NULL,
  `language` varchar(255) DEFAULT NULL,
  `page_length` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `books`
--

INSERT INTO `books` (`is_active`, `id`, `author`, `description`, `img_url`, `language`, `page_length`, `status`, `title`) VALUES
(b'1', 1, 'J.K. Rowling', 'Harry Potter has never even heard of Hogwarts when the letters start dropping on the doormat at number four, Privet Drive.', 'https://m.media-amazon.com/images/I/5165He67NEL._SY346_.jpg', 'English', '345 pages', 'BORROWED', 'Harry Potter'),
(b'1', 2, 'Rudyard Kipling', 'Join Mowgli the Man-cub as he embarks on a dangerous journey through the jungle to escape the man-eating tiger Shere Khan.', 'https://m.media-amazon.com/images/I/51cCdjGzuVL._SX364_BO1,204,203,200_.jpg', 'English', '432 pages', 'AVAILABLE', 'The Jungle Book'),
(b'1', 3, 'R.L. Stevenson', 'Think of the high seas and of a buccaneer ship; of a wild seaman with a sea chest full of gold; of Long John Silver.', 'https://m.media-amazon.com/images/I/61r44FrbhLL._SX302_BO1,204,203,200_.jpg', 'English', '247 pages', 'AVAILABLE', 'Treasure Island'),
(b'0', 4, 'Paulo Coelho', 'Paulo Coelho\'s enchanting novel has inspired a devoted following around the world. This story, dazzling in its powerful simplicity and inspiring wisdom.', 'https://m.media-amazon.com/images/I/410llGwMZGL._SY264_BO1,204,203,200_QL40_FMwebp_.jpg', 'English', '342 pages', 'AVAILABLE', 'The Alchemist'),
(b'1', 5, 'Ankur Warikoo', 'Ankur Warikoo is an entrepreneur and content creator whose deep, witty and brutally honest thoughts on success and failure, money and investing, self-awareness and personal relationships have made him one of India’s top personal brands. ', 'https://m.media-amazon.com/images/I/41oYp387k8L._SY264_BO1,204,203,200_QL40_FMwebp_.jpg', 'English', '196 pages', 'AVAILABLE', 'Do Epic Shit'),
(b'1', 6, 'Héctor García', 'THE INTERNATIONAL BESTSELLER We all have an ikigai. It\'s the Japanese word for ‘a reason to live’ or ‘a reason to jump out of bed in the morning’. It’s the place where your needs, desires, ambitions, and satisfaction meet. A place of balance.', 'https://m.media-amazon.com/images/I/51xwGSNX-EL._SX356_BO1,204,203,200_.jpg', 'English', '239 pages', 'AVAILABLE', 'Ikigai'),
(b'1', 7, 'Seth Godin', 'In this compelling, accessible and purpose-filled book bestselling business author Seth Godin shows how you have the potential to make a big difference-and make yourself indispensable in the process-wherever you are.', 'https://m.media-amazon.com/images/I/51fGhgWMZKL._SX317_BO1,204,203,200_.jpg', 'English', '301 pages', 'AVAILABLE', 'LINCHPIN'),
(b'1', 8, 'Dr. David Jeremiah', 'An Instant Wall Street Journal, USA Today, and Publishers Weekly Bestseller', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcSqH_Pm3icLqY9921UbWIGdVbkwWf4mrOQYU9FxFvDtLHXoN1Vg', 'English', '269 pages', 'AVAILABLE', 'The Great Disappearance'),
(b'1', 9, 'Adam Grant', 'This brilliant book will shatter your assumptions about what it takes to improve and succeed.', 'https://m.media-amazon.com/images/I/71UwB47AKCL._SY522_.jpg', 'English', '212 pages', 'AVAILABLE', 'Hidden Potential');

-- --------------------------------------------------------

--
-- Table structure for table `borrow-books`
--

CREATE TABLE `borrow-books` (
  `borrow_date` date DEFAULT NULL,
  `due_date` date DEFAULT NULL,
  `return_date` date DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `borrow-books`
--

INSERT INTO `borrow-books` (`borrow_date`, `due_date`, `return_date`, `book_id`, `id`, `user_id`) VALUES
('2023-10-26', '2023-11-02', '2023-10-26', 2, 1, 1),
('2023-10-26', '2023-11-02', '2023-10-26', 1, 2, 1),
('2023-10-26', '2023-11-02', '2023-10-26', 3, 3, 1),
('2023-10-26', '2023-11-02', '2023-10-26', 4, 4, 1),
('2023-10-26', '2023-11-02', '2023-10-26', 5, 5, 1),
('2023-10-26', '2023-11-02', '2023-10-26', 6, 6, 1),
('2023-10-26', '2023-11-02', '2023-10-26', 7, 7, 1),
('2023-10-26', '2023-11-02', '2023-10-26', 8, 8, 1),
('2023-10-26', '2023-11-02', NULL, 1, 9, 1),
('2023-10-26', '2023-11-02', '2023-10-26', 3, 10, 1);

-- --------------------------------------------------------

--
-- Table structure for table `reserve-books`
--

CREATE TABLE `reserve-books` (
  `reserve_date` date DEFAULT NULL,
  `book_id` bigint(20) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `reviews`
--

CREATE TABLE `reviews` (
  `date` date DEFAULT NULL,
  `rating` double DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `review-books` bigint(20) DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `review` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `reviews`
--

INSERT INTO `reviews` (`date`, `rating`, `id`, `review-books`, `user_id`, `review`) VALUES
('2023-10-26', 5, 1, 2, 1, 'Nice Book...'),
('2023-10-26', 4.9, 2, 1, 1, 'Recommended.'),
('2023-10-26', 4.7, 3, 3, 1, 'Good Book.'),
('2023-10-26', 5, 4, 4, 1, 'Great book..'),
('2023-10-26', 3.9, 5, 5, 1, 'Average Book....You Can try'),
('2023-10-26', 5, 6, 8, 1, 'Awesome book'),
('2023-10-26', 4.5, 7, 7, 1, 'Good Book'),
('2023-10-26', 5, 8, 6, 1, 'Read This Book...Recommended');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `address`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES
(1, 'Dhaka', 'shadril@outlook.com', 'Shadril', 'Hassan', '$2a$10$WkTwAbt5FHFyFZMgG8SR3OCJKFep38gf2xZjuwIYRnnQZ8ZA6L14a', 'CUSTOMER'),
(2, 'Dhaka', 'john@outlook.com', 'John', 'Doe', '$2a$10$nhCPto4JVn4RMVtHl6ve6Oo6B99xsfZfpuujkO1CsnzSEJw2iWnJG', 'CUSTOMER'),
(3, 'Dhaka', 'admin@outlook.com', 'Avijit', 'Doe', '$2a$10$dRvx4v2YOJ7qq2bFaAtBW.RcCpgYkmtw2FUOyIpptXoQN7RMLxd1u', 'ADMIN'),
(4, 'Dhaka', 'shifat@outlook.com', 'Shadril', 'Shifat', '$2a$10$QZShHMP/CEPmjBioHpSVmuazbfUl.whlshYbhMXCf1hnJREz47rbC', 'CUSTOMER');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `books`
--
ALTER TABLE `books`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `borrow-books`
--
ALTER TABLE `borrow-books`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKravnxluxpqb2wy0f47wcxjewy` (`book_id`),
  ADD KEY `FKftgifmamln14l91rt0tnw83r3` (`user_id`);

--
-- Indexes for table `reserve-books`
--
ALTER TABLE `reserve-books`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKhvdy2lavch1genq4bg42tlk4m` (`book_id`),
  ADD KEY `FKk3dasbibmow3ftm9p60lldu2s` (`user_id`);

--
-- Indexes for table `reviews`
--
ALTER TABLE `reviews`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKflaooyu09yui410cua2m3hsux` (`review-books`),
  ADD KEY `FKcgy7qjc1r99dp117y9en6lxye` (`user_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `books`
--
ALTER TABLE `books`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `borrow-books`
--
ALTER TABLE `borrow-books`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT for table `reserve-books`
--
ALTER TABLE `reserve-books`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `reviews`
--
ALTER TABLE `reviews`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=9;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `borrow-books`
--
ALTER TABLE `borrow-books`
  ADD CONSTRAINT `FKftgifmamln14l91rt0tnw83r3` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKravnxluxpqb2wy0f47wcxjewy` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`);

--
-- Constraints for table `reserve-books`
--
ALTER TABLE `reserve-books`
  ADD CONSTRAINT `FKhvdy2lavch1genq4bg42tlk4m` FOREIGN KEY (`book_id`) REFERENCES `books` (`id`),
  ADD CONSTRAINT `FKk3dasbibmow3ftm9p60lldu2s` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `reviews`
--
ALTER TABLE `reviews`
  ADD CONSTRAINT `FKcgy7qjc1r99dp117y9en6lxye` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKflaooyu09yui410cua2m3hsux` FOREIGN KEY (`review-books`) REFERENCES `books` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
