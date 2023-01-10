package com.example.demo.types;

public class Attend {
        public int id;
        public int subject;
        public String datetime;
        public int student;
        public String attended;

        public Attend(int id, int subject, String datetime, int student, String attended) {
                this.id = id;
                this.subject = subject;
                this.datetime = datetime;
                this.student = student;
                this.attended = attended;
        }
}

