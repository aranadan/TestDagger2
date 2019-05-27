package com.fox.studio.testdagger2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import dagger.Component
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    //отмечаю что это поле будет использовать зависимость
    @Inject
    lateinit var info: Info

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //This Static Class will provide the Builder to build MagicBox
        DaggerMagicBox.create().inject(this)
        tv.text = info.text
    }
}
// аннотация @Inject сообщит Dagger какие зависимости должны быть предоставлены зависимому объекту.
//Этот интерфейс будет реализован классом, который сгенерирует Dagger 2
// внедрение с использованием конструктора
class Info @Inject constructor() {
    val text = "Hello Dagger 2"
}

/*Это аннотация используется для интерфейса, который объединит все части процесса внедрения зависимостей.
При использовании данной аннотации мы определяем из каких модулей или других компонентов будут браться зависимости.*/
@Component
interface MagicBox {
    fun inject(app: MainActivity)
}