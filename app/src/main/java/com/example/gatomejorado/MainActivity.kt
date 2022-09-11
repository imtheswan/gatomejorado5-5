package com.example.gatomejorado

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

private var tablero = arrayOf(
    arrayOf(0,0,0,0,0),
    arrayOf(0,0,0,0,0),
    arrayOf(0,0,0,0,0),
    arrayOf(0,0,0,0,0),
    arrayOf(0,0,0,0,0))

private var casillas = arrayOf<Array<Button>>(arrayOf(), arrayOf(), arrayOf(), arrayOf(), arrayOf())

private var turno = 0 // 0 : jugador 1

class MainActivity : AppCompatActivity() {
    private lateinit var jugando: TextView
    private lateinit var jugador1:TextView
    private lateinit var jugador2:TextView
    private lateinit var reiniciar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        casillas[0] =
            arrayOf(findViewById(R.id.c1f1), findViewById(R.id.c2f1), findViewById(R.id.c3f1), findViewById(R.id.c4f1), findViewById(R.id.c5f1))
        casillas[1] =
            arrayOf(findViewById(R.id.c1f2), findViewById(R.id.c2f2), findViewById(R.id.c3f2), findViewById(R.id.c4f2), findViewById(R.id.c5f2))
        casillas[2] =
            arrayOf(findViewById(R.id.c1f3), findViewById(R.id.c2f3), findViewById(R.id.c3f3), findViewById(R.id.c4f3), findViewById(R.id.c5f3))
        casillas[3] =
            arrayOf(findViewById(R.id.c1f4), findViewById(R.id.c2f4), findViewById(R.id.c3f4), findViewById(R.id.c4f4), findViewById(R.id.c5f4))
        casillas[4] =
            arrayOf(findViewById(R.id.c1f5), findViewById(R.id.c2f5), findViewById(R.id.c3f5), findViewById(R.id.c4f5), findViewById(R.id.c5f5))

        reiniciar = findViewById(R.id.restart)
        reiniciar.setOnClickListener{restartGame()}
        jugando = findViewById(R.id.jugando)
        jugador1 = findViewById(R.id.jugador1)
        jugador2 = findViewById(R.id.jugador2)

        restartGame()
    }

    private fun restartGame(){
        for (a in casillas)
            for (b in a) {
                b.text = getText(R.string.empty)
                b.setOnClickListener { changeValue(b) }
            }
        for (x in 0..4){
            for (y in 0..4){
                tablero[x][y] = 0
            }
        }
        jugador1.text = getString(R.string.jugador1)
        jugador2.text = getString(R.string.jugador2)
        jugando.text = getString(R.string.jugador1)

        turno = 0
    }

    private fun checkHorizontalWin():Int{
        var winning = false
        for (fila in tablero){
            for (casilla in 1..3){
                if(fila[casilla] == 1){
                    winning = true
                }
                else{
                    winning = false
                    break
                }
            }
            if(((winning) and (fila[0] == 1)) or ((winning) and (fila[4] == 1))){
                jugador1.text = getString(R.string.ganaste)
                jugador2.text = getString(R.string.perdiste)
                jugando.text = getString(R.string.empty)
            }
        }

        winning = false
        for (fila in tablero){
            for (casilla in 1..3){
                if(fila[casilla] == 2){
                    winning = true
                }
                else{
                    winning = false
                    break
                }
            }
            if(((winning) and (fila[0] == 2)) or ((winning) and (fila[4] == 2))){
                jugador2.text = getString(R.string.ganaste)
                jugador1.text = getString(R.string.perdiste)
                jugando.text = getString(R.string.empty)
                return 0
            }
        }
        return 0
    }

    private fun checkVerticalWin(): Int{
        var winning = false
        for (casilla in 0..4){
            for (fila in 1..3){
                if(tablero[fila][casilla] == 1){
                    winning = true
                } else {
                    winning = false
                    break
                }
            }
            if(((winning) and (tablero[0][casilla] == 1)) or ((winning) and (tablero[4][casilla] == 1))){
                jugador1.text = getString(R.string.ganaste)
                jugador2.text = getString(R.string.perdiste)
                jugando.text = getString(R.string.empty)
                return 0
            }
        }

        winning = false
        for (casilla in 0..4){
            for (fila in 1..3){
                if(tablero[fila][casilla] == 2){
                    winning = true
                } else {
                    winning = false
                    break
                }
            }
            if(((winning) and (tablero[0][casilla] == 2)) or ((winning) and (tablero[4][casilla] == 2))){
                jugador2.text = getString(R.string.ganaste)
                jugador1.text = getString(R.string.perdiste)
                jugando.text = getString(R.string.empty)
                return 0
            }
        }
        return 0
    }

    private fun checkDiagonalWin():Int{
        var winni = false

        for (i in 1..3) winni = tablero[i][i] == 1
        if (winni and ((tablero[0][0] == 1) or (tablero[4][4] == 1))){
            jugador1.text = getString(R.string.ganaste)
            jugador2.text = getString(R.string.perdiste)
            jugando.text = getString(R.string.empty)
            return 0
        }
        winni = false
        for (i in 1..4) winni = tablero[i- 1][i] == 1
        if (winni){
            jugador1.text = getString(R.string.ganaste)
            jugador2.text = getString(R.string.perdiste)
            jugando.text = getString(R.string.empty)
            return 0
        }

        for (i in 0..3) winni = tablero[i + 1][i] == 1
        if (winni){
            jugador1.text = getString(R.string.ganaste)
            jugador2.text = getString(R.string.perdiste)
            jugando.text = getString(R.string.empty)
            return 0
        }

        for (i in 1..3) winni = tablero[(i - 4) * -1][i] == 2
        if (winni and ((tablero[4][0] == 2) or (tablero[0][4] == 2))){
            jugador1.text = getString(R.string.perdiste)
            jugador2.text = getString(R.string.ganaste)
            jugando.text = getString(R.string.empty)
            return 0
        }
        winni = false
        for (i in 1..4) winni = tablero[(i- 5)* -1][i] == 1
        if (winni){
            jugador1.text = getString(R.string.perdiste)
            jugador2.text = getString(R.string.ganaste)
            jugando.text = getString(R.string.empty)
            return 0
        }

        for (i in 0..3) winni = tablero[(i - 3) * -1][i] == 1
        if (winni){
            jugador1.text = getString(R.string.ganaste)
            jugador2.text = getString(R.string.perdiste)
            jugando.text = getString(R.string.empty)
            return 0
        }

        return 0
    }

    private fun changeValue(boton:Button){
        var posx= 0
        var posy = 0
        var terminate = false
        //jugador1.text = boton.toString()
        //jugador2.text = (boton == casillas[0][0]).toString()
        for(a in casillas){
            for (b in a){
                if(casillas[posx][posy] == boton){
                    if (turno == 0){
                        jugando.text = getString(R.string.jugador2)
                        tablero[posx][posy] = 1
                        boton.text = getString(R.string.x)
                        turno = 1
                    } else{
                        jugando.text = getString(R.string.jugador1)
                        tablero[posx][posy] = 2
                        boton.text = getString(R.string.o)
                        turno = 0
                    }
                    terminate = true
                    break
                }
                posx ++
            }
            posx = 0
            if (terminate){
                break}
            posy ++
        }
        checkHorizontalWin()
        checkVerticalWin()
        checkDiagonalWin()
    }
}