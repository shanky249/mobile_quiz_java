CREATE TABLE IF NOT EXISTS app_user (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL
);

CREATE TABLE IF NOT EXISTS question (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    content VARCHAR(255) NOT NULL,
    image VARCHAR(5000),
    options JSON NOT NULL,
    correct_option_index INT NOT NULL,
    remaining_time INT NOT NULL
);

CREATE TABLE IF NOT EXISTS stats (
    game_id BIGINT,
    username VARCHAR(255),
    question_ids VARCHAR(255),
    selected_options TEXT,
    total_correct_answers INT,
    total_lifelines_used INT,
    PRIMARY KEY (game_id)
);

CREATE TABLE IF NOT EXISTS game (
    game_id UUID PRIMARY KEY,
    questions JSONB,
    fifty_fifty_used BOOLEAN,
    extra_time_used BOOLEAN,
    correct_answers INT
);

INSERT INTO app_user (username, password)
VALUES
('admin', 'admin123'),
('user', 'user123');

INSERT INTO questions (content, image, options, correct_option_index, remaining_time)
VALUES
('What is the capital of Afghanistan?', NULL, '["Kabul", "Tirana", "Algiers", "Andorra la Vella"]', 0, 15),
('What is the capital of Albania?', NULL, '["Tirana", "Algiers", "Andorra la Vella", "Luanda"]', 2, 15),
('What is the capital of Algeria?', NULL, '["Algiers", "Andorra la Vella", "Luanda", "St. John''s"]', 3, 15),
('What is the capital of Andorra?', NULL, '["Andorra la Vella", "Luanda", "St. John''s", "Buenos Aires"]', 1, 15),
('What is the capital of Angola?', NULL, '["Luanda", "St. John''s", "Buenos Aires", "Yerevan"]', 0, 15),
('What is the capital of Antigua and Barbuda?', NULL, '["St. John''s", "Buenos Aires", "Yerevan", "Canberra"]', 2, 15),
('What is the capital of Argentina?', NULL, '["Buenos Aires", "Yerevan", "Canberra", "Vienna"]', 0, 15),
('What is the capital of Armenia?', NULL, '["Yerevan", "Canberra", "Vienna", "Baku"]', 2, 15),
('What is the capital of Australia?', NULL, '["Canberra", "Vienna", "Baku", "Nassau"]', 0, 15),
('What is the capital of Austria?', NULL, '["Vienna", "Baku", "Nassau", "Manama"]', 3, 15),
('What is the capital of Azerbaijan?', NULL, '["Baku", "Nassau", "Manama", "Dhaka"]', 2, 15),
('What is the capital of Bahamas?', NULL, '["Nassau", "Manama", "Dhaka", "Bridgetown"]', 1, 15),
('What is the capital of Bahrain?', NULL, '["Manama", "Dhaka", "Bridgetown", "Minsk"]', 0, 15),
('What is the capital of Bangladesh?', NULL, '["Dhaka", "Bridgetown", "Minsk", "Brussels"]', 1, 15),
('What is the capital of Barbados?', NULL, '["Bridgetown", "Minsk", "Brussels", "Belmopan"]', 2, 15),
('What is the capital of Belarus?', NULL, '["Minsk", "Brussels", "Belmopan", "Porto-Novo"]', 0, 15),
('What is the capital of Belgium?', NULL, '["Brussels", "Belmopan", "Porto-Novo", "Thimphu"]', 1, 15),
('What is the capital of Belize?', NULL, '["Belmopan", "Porto-Novo", "Thimphu", "La Paz"]', 3, 15),
('What is the capital of Benin?', NULL, '["Porto-Novo", "Thimphu", "La Paz", "Sarajevo"]', 0, 15),
('What is the capital of Bhutan?', NULL, '["Thimphu", "La Paz", "Sarajevo", "Gaborone"]', 2, 15),
('What is the capital of Bolivia?', NULL, '["La Paz", "Sarajevo", "Gaborone", "Brasília"]', 1, 15),
('What is the capital of Bosnia and Herzegovina?', NULL, '["Sarajevo", "Gaborone", "Brasília", "Bandar Seri Begawan"]', 3, 15),
('What is the capital of Botswana?', NULL, '["Gaborone", "Brasília", "Bandar Seri Begawan", "Sofia"]', 0, 15),
('What is the capital of Brazil?', NULL, '["Brasília", "Bandar Seri Begawan", "Sofia", "Ouagadougou"]', 2, 15),
('What is the capital of Brunei?', NULL, '["Bandar Seri Begawan", "Sofia", "Ouagadougou", "Bujumbura"]', 1, 15),
('What is the capital of Bulgaria?', NULL, '["Sofia", "Ouagadougou", "Bujumbura", "Praia"]', 2, 15),
('What is the capital of Burkina Faso?', NULL, '["Ouagadougou", "Bujumbura", "Praia", "Phnom Penh"]', 0, 15),
('What is the capital of Burundi?', NULL, '["Bujumbura", "Praia", "Phnom Penh", "Kabul"]', 2, 15),
('What is the capital of Cabo Verde?', NULL, '["Praia", "Phnom Penh", "Kabul", "Tirana"]', 1, 15),
('What is the capital of Cambodia?', NULL, '["Phnom Penh", "Kabul", "Tirana", "Algiers"]', 3, 15);
