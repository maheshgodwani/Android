fun main() {
    var choice: Int

    do {
        println("------------------------------")
        println("Welcome to this Prog.")
        println("------------------------------")
        println("Enter 1 : Addition")
        println("Enter 2 : Subtraction")
        println("Enter 3 : Multiplication")
        println("Enter 4 : Division")
        println("Enter 5 : Exit")
        println()
        print("Enter your Choice : ")
        choice = readln().toInt()

        if (choice in 1..4) {
            print("Enter first Num : ")
            val num1 = readln().toDouble()
            print("Enter Second Num : ")
            val num2 = readln().toDouble()

            when (choice) {
                1 -> println("Addition = ${num1 + num2}")
                2 -> println("Subtraction = ${num1 - num2}")
                3 -> println("Multiplication = ${num1 * num2}")
                4 -> {
                    if (num2 != 0.0) {
                        println("Division = ${num1 / num2}")
                    } else {
                        println("Error: Division by zero is not allowed")
                    }
                }
            }
        } else if (choice != 5) {
            println("Invalid choice. Please try again.")
        }

        println()
    } while (choice != 5)

    println("Exiting the program. Goodbye!")
}
