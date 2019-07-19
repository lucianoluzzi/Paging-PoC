package br.com.lucianoluzzi.pagingtest.repository

import br.com.lucianoluzzi.pagingtest.model.entity.Person

class MockPeopleDataProvider {
    companion object {
        fun getMockedPeople(): List<Person> {
            val luciano = Person(
                1,
                "luciano",
                "",
                "messi"
            )
            val virginia = Person(
                2,
                "virginia",
                "tagliafico",
                "jesus"
            )
            val zuleica = Person(
                2,
                "cordova",
                "",
                "cordova"
            )

            return listOf(luciano, virginia, zuleica)
        }

        fun getMockedPerson(): Person {
            return Person(
                1,
                "luciano",
                "",
                "messi"
            )
        }
    }
}