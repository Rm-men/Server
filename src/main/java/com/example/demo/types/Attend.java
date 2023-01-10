package com.example.demo.types;

public class Attend {
        private final int id;
        private final int subject;
        private final String datetime;
        private final int student;
        private final String attended;

        public Attend(int id, int subject, String datetime, int student, String attended) {
                this.id = id;
                this.subject = subject;
                this.datetime = datetime;
                this.student = student;
                this.attended = attended;
        }

        public int getId() {
                return id;
        }

        public int getSubject() {
                return subject;
        }

        public String getDatetime() {
                return datetime;
        }

        public int getStudent() {
                return student;
        }

        public String getAttended() {
                return attended;
        }
}

