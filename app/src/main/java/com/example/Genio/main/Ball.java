package com.example.Genius.main;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Point;

/**
 * A classe Ball representa um gerênciador da imagem 'ball.png'.
 * A classe tem como base:
 * - 3 instâncias da classe Point que armazenam as coordenadas do local atual da imagem, as
 *   coordenadas para calcular sua aceleração e velocidade;
 * - 1 intância constante da classe Point que armazena a escala da imagem;
 * - 1 instância da classe Bitmap que armazena a imagem em si;
 * - 2 inteiros que armazenam o ângulo da animação na imagem e a última cor que a imagem esteve;
 * - 1 inteiro estático que armazena o raio do desenho da imagem;
 *
 * Instâncias desta classe permitem gerênciar os dados da imagem no jogo.
 *
 * @author Daniel Samogin Campioni e Pedro Luiz Pezoa
 * @since 2017
 * @version 1.0
 */

public class Ball
{
    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////ATRIBUTOS///////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Atributos Point chamados 'local', 'coordVel' e 'coordAce', suas funções são armazenar as
     * coordenadas da imagem, da velocidade e da aceleração, respectivamente
     */
    private Point local, coordVel, coordAce;

    /**
     * Atributo Bitmap chamado 'textura', sua função é armazenar a imagem
     */
    private Bitmap textura;

    /**
     * Atributo constante Point chamado 'escala', sua função é armazenar a escala da imagem
     */
    final Point escala = new Point(75, 75);

    /**
     * Atributos ints chamados 'angulo' e 'lastColor', suas funções são armazenar os ângulos do
     * circulo da animação e a última cor que a imagem esteve, respectivamente
     */
    private int angulo, lastColor;

    /**
     * Atributo estático int chamado 'raio', sua função é armazenar o escala do raio da imagem
     */
    private static int raio;
    private static int diametro;

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ///////////////////////////////////////////CONSTRUTOR///////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Contrutor da classe Ball que instância todos os atributos com seus respectivos valores
     * @param res classe Resources que representa o diretório das imagens
     */
    public Ball(Resources res)
    {
        this.local = new Point(-50,-50);
        this.coordVel = new Point(0,0);
        this.coordAce = new Point(0,0);

        this.angulo = 0;
        this.lastColor = Color.BLUE;
        raio = 38;
        diametro = raio*2;

        this.textura = Bitmap.createScaledBitmap(BitmapFactory.decodeResource
                (res, R.drawable.ball), escala.x, escala.y, true);
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////GETTERS E SETTERS///////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Método que retorna o valor do atributo 'local'
     * @return um Point que representa as coordenadas atuais da imagem
     */
    public Point getLocal() {
		return this.local;
	}

    /**
     * Método que retorna o valor do atributo 'textura'
     * @return um Bitmap que representa a imagem
     */
    public Bitmap getTextura() {
        return this.textura;
    }

    public void setTextura(Bitmap novaTextura) {
        this.textura = novaTextura;
    }

    /**
     * Método estático que retorna o valor do atributo 'raio'
     * @return um inteiro que representa o raio da imagem
     */
    public static int getRaio() { return raio; }

    /**
     * Método que retorna o valor do atributo 'angulo'
     * @return um inteiro que representa o ândulo da animação
     */
    public int getAngulo() {
        return this.angulo;
    }

    /**
     * Método que retorna o valor do atributo 'lastColor'
     * @return um inteiro que representa a última cor que a imagem ficou
     */
    public int getLastColor() {
        return this.lastColor;
    }

    /**
     * Método que atribui dados ao atributo 'inicioDeFase'
     * @param _local Point que representa as novas coordenadas da imagem
     */
	public void setLocal(Point _local) {
		this.local = _local;
	}

    /**
     * Método que atribui dados ao atributo 'coordAce'
     * @param _coordAce Point que representa as novas coordenadas da aceleração
     */
	public void setCoordAce(Point _coordAce) {
		this.coordAce = _coordAce;
	}

    /**
     * Método que atribui dados ao atributo 'angulo'
     * @param _angulo inteiro que representa o novo ângulo da animação
     */
    public void setAngulo(int _angulo) {
        this.angulo = _angulo;
    }

    /**
     * Método que atribui dados ao atributo 'lastColor'
     * @param _lastColor inteiro que representa a cor anterior que a imagem ficou
     */
    public void setLastColor(int _lastColor) {
        this.lastColor = _lastColor;
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////MÉTODOS PRINCIPAIS/////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////////////

    /**
     * Método que atualiza as coordenadas da imagem atualizando também as coordenadas da velocidade
     * e da aceleração
     */
    public void atualizaLocal()
    {
        float frameTime = 0.666f;

        this.coordVel.x += this.coordAce.x * frameTime;
        this.coordVel.y += this.coordAce.y * frameTime;

        float xS = (this.coordVel.x / 2) * frameTime;
        float yS = (this.coordVel.y / 2) * frameTime;

        this.local.x -= xS;
        this.local.y -= yS;
    }

    /**
     * Método que realiza uma série de verificações sobre colisões da imagem com o canvas do celular
     */
    public void verColisoes()
    {
        if (this.local.x + diametro > JogoActivity.getSize().x) {
            this.local.x = JogoActivity.getSize().x - diametro;
            this.coordAce.x = this.coordVel.x = 0;
        }
        else if (this.local.x < 0) {
            this.local.x = 0;
            this.coordAce.x = this.coordVel.x = 0;
        }

        if (this.local.y + diametro > JogoActivity.getSize().y) {
            this.local.y = JogoActivity.getSize().y - diametro;
            this.coordAce.y = this.coordVel.y = 0;
        }
        else if (this.local.y < 0) {
            this.local.y = 0;
            this.coordAce.y = this.coordVel.y = 0;
        }
    }

    /**
     * Método que faz uma série de verificações para saber em qual cor a imagem está
     * @param cor inteiro que representa a cor que a imagem esta atualmente
     * @return um boolean que representa se a cor passada como parãmetro é a mesma verificada
     */
    public boolean estaEm(int cor)
    {
        if (cor == Color.BLUE)
        {
            if (this.local.x + raio >= 0 &&
                this.local.y + raio >= 0 &&
                this.local.x + raio <= JogoActivity.getSize().x/2 &&
                this.local.y + raio <= JogoActivity.getSize().y/2)
                return true;

            return false;
        }
        else if (cor == Color.GREEN)
        {
            if (this.local.x + raio >= JogoActivity.getSize().x/2 &&
                this.local.y + raio >= 0 &&
                this.local.x + raio <= JogoActivity.getSize().x &&
                this.local.y + raio <= JogoActivity.getSize().y/2)
                return true;

            return false;
        }
        else if (cor == Color.RED)
        {
            if (this.local.x + raio >= 0 &&
                this.local.y + raio >= JogoActivity.getSize().y/2 &&
                this.local.x + raio <= JogoActivity.getSize().x/2 &&
                this.local.y + raio <= JogoActivity.getSize().y)
                return true;

            return false;
        }
        else if (cor == Color.YELLOW)
        {
            if (this.local.x + raio >= JogoActivity.getSize().x/2 &&
                this.local.y + raio >= JogoActivity.getSize().y/2 &&
                this.local.x + raio <= JogoActivity.getSize().x &&
                this.local.y + raio <= JogoActivity.getSize().y)
                return true;

            return false;
        }
        return false;
    }

}