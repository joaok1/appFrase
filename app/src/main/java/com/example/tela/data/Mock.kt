package com.example.tela.data

import com.example.tela.`object`.Constantes
import java.util.Random

class Phrase(val description:String, val categoryId: Int)

private val inifinit:String = "Meu bem, libera o Butico!"
private val sentiment:String = "Um sorriso é a luz que ilumina o rosto e aquece o coração, uma linguagem universal que transcende as palavras e une as almas."
private val sunny:String = "O sol, incansável e radiante, é a centelha da vida que abraça a Terra todos os dias, pintando o céu com suas cores douradas e nutrindo a essência de tudo o que existe."

class Mock {
    val mListPhrase = listOf<Phrase>(
        Phrase(inifinit,Constantes.FILTER.INFINITY),
        Phrase(sentiment,Constantes.FILTER.SENTIMENT),
        Phrase(sunny,Constantes.FILTER.SUNNY)

    )
    fun number():String {
        val start = 0
        val end = Constantes.FILTER.SUNNY
        var numberFinally =  numberFinal(start,end)
        return mListPhrase[numberFinally].description
    }

    fun numberFinal(start: Int, end: Int): Int {
        val numberResult = kotlin.random.Random.nextInt(start,end)
        return numberResult
    }
}
