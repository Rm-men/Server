package com.example.demo.types;

public class Attend {
        private int id;
        private int subject;
        private String datetime;
        private int student;
        private String attended;

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

        public void setId(int id) {
                this.id = id;
        }

        public int getSubject() {
                return subject;
        }

        public void setSubject(int subject) {
                this.subject = subject;
        }

        public String getDatetime() {
                return datetime;
        }

        public void setDatetime(String datetime) {
                this.datetime = datetime;
        }

        public int getStudent() {
                return student;
        }

        public void setStudent(int student) {
                this.student = student;
        }

        public String getAttended() {
                return attended;
        }

        public void setAttended(String attended) {
                this.attended = attended;
        }
}

