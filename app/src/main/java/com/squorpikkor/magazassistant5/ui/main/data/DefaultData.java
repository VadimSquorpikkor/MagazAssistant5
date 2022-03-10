package com.squorpikkor.magazassistant5.ui.main.data;

import com.squorpikkor.magazassistant5.ui.main.entities.Employee;

import java.util.ArrayList;

class DefaultData {

   static ArrayList<Employee> employeesDefault() {
       ArrayList<Employee> employees = new ArrayList<>();
       employees.add(new Employee("1", "Карпук И.Н.", "1", "01111"));
       employees.add(new Employee("2", "Король В.А.", "1", "11111"));
       employees.add(new Employee("3", "Курак Л.И.", "1", "11111"));
       employees.add(new Employee("4", "Лютаревич А.Л.", "1", "11111"));
       employees.add(new Employee("5", "Шпилевская Л.С.", "1", "11111"));
       employees.add(new Employee("6", "Яцкевич И.В.", "1", "11111"));

       employees.add(new Employee("7", "Алисевич Д.В.", "2", "10101"));
       employees.add(new Employee("8", "Алисевич О.В.", "2", "10101"));
       employees.add(new Employee("9", "Дементьев В.Ю.", "2", "11111"));
       employees.add(new Employee("10", "Долгий С.В.", "2", "11111"));
       employees.add(new Employee("11", "Махнюков И.С.", "2", "11111"));
       employees.add(new Employee("12", "Мороз Ю.В.", "2", "11111"));
       employees.add(new Employee("13", "Пекарский Ю.В.", "2", "11111"));
       employees.add(new Employee("14", "Праневич А.А.", "2", "11111"));
       employees.add(new Employee("15", "Шустов М.В.", "2", "00110"));
       employees.add(new Employee("16", "Яворский А.В.", "2", "11111"));
       employees.add(new Employee("17", "Ячминев М.В.", "2", "11111"));

       employees.add(new Employee("18", "Барсуков С.Н.", "3", "11111"));
       employees.add(new Employee("19", "Буров Л.В.", "3", "11111"));
       employees.add(new Employee("20", "Буров М.Л.", "3", "11111"));
       employees.add(new Employee("21", "Видасев Р.В.", "3", "11111"));
       employees.add(new Employee("22", "Денисенко С.В.", "3", "11111"));
       employees.add(new Employee("23", "Дыба Н.А.", "3", "11111"));
       employees.add(new Employee("24", "Зуевский В.И.", "3", "11111"));
       employees.add(new Employee("25", "Каменщиков Е.С.", "3", "11111"));
       employees.add(new Employee("26", "Кишкилевич В.П.", "3", "11111"));
       employees.add(new Employee("27", "Колосовский А.Г.", "3", "11111"));
       employees.add(new Employee("28", "Любач В.Г.", "3", "11111"));
       employees.add(new Employee("29", "Матвеенко А.М,", "3", "11111"));
       employees.add(new Employee("30", "Муравецкий М.В,", "3", "11111"));
       employees.add(new Employee("31", "Поляков В.В,", "3", "11111"));
       employees.add(new Employee("32", "Рычик А.А.", "3", "11111"));
       employees.add(new Employee("33", "Рябов А.К.", "3", "11111"));
       employees.add(new Employee("34", "Станкевич Ю.Б.", "3", "11111"));
       employees.add(new Employee("35", "Фролов И.А.", "3", "11111"));
       employees.add(new Employee("36", "Юревич С.А,", "3", "11111"));

       employees.add(new Employee("37", "Крот А.С.", "4", "11111"));
       employees.add(new Employee("38", "Штылев Г.Н.", "4", "11111"));
       employees.add(new Employee("39", "Антонова О.Н.", "4", "11111"));

       return employees;
   }

}
