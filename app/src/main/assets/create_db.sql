/* sqlite3 mlb.db < create_db.sql */

CREATE TABLE Quizes
(
	QuizIndex INTEGER NOT NULL,
	QuizName TEXT NOT NULL,
	Image TEXT NOT NULL,
	CONSTRAINT Quizes_PK PRIMARY KEY (QuizIndex)
);

CREATE TABLE Questions
(
	QuestionIndex INTEGER NOT NULL,
	QuizIndex INTEGER NOT NULL,
	QuestionText TEXT NOT NULL,
	ScoreMode TEXT(1) NOT NULL,
	CONSTRAINT Questions_PK PRIMARY KEY (QuestionIndex,QuizIndex),
	CONSTRAINT Questions_Quizes_FK FOREIGN KEY (QuizIndex) REFERENCES Quizes(QuizIndex) ON DELETE RESTRICT ON UPDATE RESTRICT
);

CREATE TABLE Answers
(
	AnswerIndex INTEGER NOT NULL,
	AnswerText TEXT NOT NULL,
	CONSTRAINT Answers_PK PRIMARY KEY (AnswerIndex)
);

/* version where questions don't all have the same answers
CREATE TABLE Answers
(
	AnswerIndex INTEGER NOT NULL,
	QuestionIndex INTEGER NOT NULL,
	QuizIndex INTEGER NOT NULL,
	AnswerText TEXT NOT NULL,
	CONSTRAINT Answers_PK PRIMARY KEY (AnswerIndex,QuestionIndex,QuizIndex),
	CONSTRAINT Answers_Questions_FK FOREIGN KEY (QuestionIndex) REFERENCES Questions(QuestionIndex) ON DELETE RESTRICT ON UPDATE RESTRICT,
	CONSTRAINT Answers_Quizes_FK FOREIGN KEY (QuizIndex) REFERENCES Quizes(QuizIndex) ON DELETE RESTRICT ON UPDATE RESTRICT
);
*/

INSERT INTO Quizes (QuizIndex, QuizName, Image) VALUES ( 0, 'Health and Personal Growth', 'health'   );
INSERT INTO Quizes (QuizIndex, QuizName, Image) VALUES ( 1, 'Money and Career',           'money'    );
INSERT INTO Quizes (QuizIndex, QuizName, Image) VALUES ( 2, 'Physical Environment',       'physical' );
INSERT INTO Quizes (QuizIndex, QuizName, Image) VALUES ( 3, 'Family and Relationships',   'family'   );

INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 0, 0, '+', 'I feel excited about my life most of the time.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 1, 0, '-', 'I feel disengaged and fearful most of the time.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 2, 0, '+', 'I feel content and appreciate with what life has taught me.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 3, 0, '-', 'I feel stressed and overwhelmed with my daily activities.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 4, 0, '+', 'I am in full control of my health and wellness.' );

INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 0, 1, '+', 'I have a plan for my business/career and l am on track.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 1, 1, '-', 'I am frustrated with my work/life balance.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 2, 1, '+', 'I organise my budget well and know exactly where my money is spent.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 3, 1, '-', 'I live week to week with no idea where my money goes.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 4, 1, '+', 'I have a vision and mission for my life.' );

INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 0, 2, '+', 'I feel at peace in my surroundings.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 1, 2, '-', 'My environment is busy and cluttered.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 2, 2, '+', 'I have a special space where l am able to sit and reflect.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 3, 2, '-', 'I do not have a place l use for reflection and creativity.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 4, 2, '-', 'I feel anxious and frustrated with my surroundings.' );

INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 0, 3, '+', 'I am energised with the connection l have with my partner/ special person.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 1, 3, '-', 'I feel that everyone around me drains my energy.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 2, 3, '+', 'I love to plan special activities with my partner/special person.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 3, 3, '-', 'I prefer to spend my free time alone.' );
INSERT INTO Questions (QuestionIndex, QuizIndex, ScoreMode, QuestionText) VALUES ( 4, 3, '-', 'I would like to spend more time with my family/friends.' );

INSERT INTO Answers (AnswerIndex, AnswerText) VALUES ( 0, 'Strongly Disagree' );
INSERT INTO Answers (AnswerIndex, AnswerText) VALUES ( 1, 'Disagree'          );
INSERT INTO Answers (AnswerIndex, AnswerText) VALUES ( 2, 'Neutral'           );
INSERT INTO Answers (AnswerIndex, AnswerText) VALUES ( 3, 'Agree'             );
INSERT INTO Answers (AnswerIndex, AnswerText) VALUES ( 4, 'Strongly Agree'    );

