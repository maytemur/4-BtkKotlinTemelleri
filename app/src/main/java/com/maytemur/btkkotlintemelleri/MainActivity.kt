package com.maytemur.btkkotlintemelleri

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Bu dosyanın içi bir şekilde silinmiş ! Dizilerde dahil Btk pratikleri KotlinOgrenmeProjesi içinde
        //Buradaki kodlar codelabs'ın "Use Lists in Kotlin" eğitiminden alındı
        //2 tip liste var biri değiştirilemez lists diğeride mutablelists
        open class Item(val name: String, val price: Int)
        class Noodles : Item("Noodles", 10) {
            override fun toString(): String {
                return name
            }
        }

        class Vegetables(vararg val toppings: String) : Item("Vegetables", 5) {
            override fun toString(): String {
                if (toppings.isEmpty()) {
                    return "$name Chef's Choice"
                } else {
                    return name + " " + toppings.joinToString()
                }
            }
        }

        class Order(val orderNumber: Int) {
            private val itemList = mutableListOf<Item>() // değiştirilebilir liste

            fun addItem(newItem: Item) {
                itemList.add(newItem)
            }

            fun addAll(newItems: List<Item>) { //read-only list of items
                itemList.addAll((newItems))
            }

            fun print() {
                println("Order #${orderNumber}")
                var total = 0
                for (item in itemList) {
                    println("${item}: $${item.price}")
                    total += item.price
                }
                println("Total: $${total}")
            }
        }

//        val noodles = Noodles()
//        val vegetables = Vegetables("Cabbage", "Sprouts", "Onion")
//        val vegetables2 = Vegetables()
//        println(noodles)
//        println(vegetables)
//        println(vegetables2)
        val order1 = Order(1)
        order1.addItem(Noodles())
        order1.print()

        println()

        val order2 = Order(2)
        order2.addItem(Noodles())
        order2.addItem(Vegetables())
        order2.print()

        println()

        val order3 = Order(3)
        val items = listOf(Noodles(), Vegetables("Carrots", "Beans", "Celery"))
        order3.addAll(items)
        order3.print()

        //veya bunu mutableList ile daha düzenli kodla yazabiliriz
        // val ordersList = mutableListOf<Order>() diye sipariş listesi yaparsak
        // Print satırlarını kaldırıp liste içinde
        //    for (order in ordersList) {
        //        order.print()
        //        println()
        //    }
        //diyerek print metoduna gönderebiliriz aynı sonucu verir. sadece "order1.print" satırlarını
        //kaldırıp ordersList.add(order1) satırını ekleyerek her bir order'ı listeye eklememiz ve
        // fun addItem(newItem: Item) satırını ve diğerini
        //fun addItem(newItem: Item) : Order
        // fun addAll(newItems: List<Item>) : Order şeklinde değiştirmemiz gerekir
    }
}