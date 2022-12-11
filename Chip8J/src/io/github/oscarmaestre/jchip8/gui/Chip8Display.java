/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package io.github.oscarmaestre.jchip8.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.BitSet;
import javax.swing.JPanel;

/**
 *
 * @author usuario
 */
public class Chip8Display extends JPanel {
    /*Use the standard WIDTH x SIZE = 64 x 32*/
    protected       int         STD_WIDTH;
    protected       int         STD_HEIGHT;
    protected       int         scale;
    protected       boolean     pixelMatrix[][];
    protected       Color       bgColor;
    protected       Color       fgColor;
    
    
    public Chip8Display(int MAX_WIDTH, int MAX_HEIGHT, int scale) {
        this.STD_WIDTH      = MAX_WIDTH;
        this.STD_HEIGHT     = MAX_HEIGHT;
        this.setScale(scale);
        this.pixelMatrix    = new boolean[this.STD_WIDTH][this.STD_HEIGHT];
        
        
        
        
        this.setBgColor(Color.BLUE);
        this.setFgColor(Color.WHITE);
        System.out.println("Todo añadido");
        this.drawLine();
    }
    
    
    public void setPixel(int x, int y){
        this.pixelMatrix[x][y]=true;
    }
    
    public void resetPixel(int x, int y){
        this.pixelMatrix[x][y]=false;
    }
    public void setScale(int newScale){
        if (newScale<1){
            newScale=1;
        }
        if (newScale>4){
            newScale=4;
        }
        this.scale=newScale;
        this.setSize(new Dimension(this.STD_WIDTH*scale, this.STD_HEIGHT*scale));
        this.repaint();
    }
    /**
     * Draws a sprite in screen
     * @return true if there was a collision, false otherwise
     */
    public boolean drawSprite(){
        boolean collision=false;
        
        return collision;
    } //End of drawSprite

    public void CLS(){
        for (int row=0; row < this.STD_HEIGHT; row++){
            for (int column = 0 ; column < this.STD_WIDTH; column++){
                this.pixelMatrix[row][column]=false;
            } //End inner for
        }//End outer for
        this.repaint();
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int row=0; row < this.STD_HEIGHT; row++){
            int scaledRow=row*this.scale;
            for (int column = 0 ; column < this.STD_WIDTH; column++){
                if (this.pixelMatrix[column][row]){
                    int scaledColumn=column*this.scale;
                    g.fillRect(scaledColumn,scaledRow,  this.scale, this.scale);
                } //If end
            } //End inner for
        }//End outer for
        System.out.println("Actualizado");
    } //End of paint method
        
    public void drawLine(){
        for (int x=5; x<20; x++){
            this.setPixel(x, 20);
        }
    }

    public Color getBgColor() {
        return bgColor;
    }

    public void setBgColor(Color bgColor) {
        this.bgColor = bgColor;
        this.setBackground(bgColor);
    }

    public Color getFgColor() {
        return fgColor;
    }

    public void setFgColor(Color fgColor) {
        this.fgColor = fgColor;
        this.setForeground(fgColor);
        
    }

    public void dibujarSprite(byte[] sprite, int x0, int y0){
        /* Al dibujar está permitido que un sprite se salga de la
        pantalla, se debe hacer que aparezcap or el otro lado
        */
        BitSet bits = BitSet.valueOf(sprite);
        int y=y0;
        int x=x0;
        System.out.println(bits.toString());
        System.out.println("Longitud en bits:"+bits.length());
        
        for (int pos = 0 ; pos < bits.length(); pos++){
            if ( (pos + 1) % 8 ==0){
                x=x0;
                y=y+1;
                System.out.println();
            }
            
            if (bits.get(pos)){
                this.pixelMatrix[x][y]=true;
                System.out.print("*");
            } else {
                this.pixelMatrix[x][y]=false;
                System.out.print(" ");
            }
            x=x+1;
            if (x>this.STD_WIDTH){
                System.out.println("Error, el sprite se sale por un lado y hay que corregirlo");
            }
        }
    }
    
    public void dibujarSprite(String[] sprite, int x0, int y0){
        
        int y=y0;
        int x=x0;
        System.out.println(sprite);
        
        for (String linea : sprite){
            x=x0;
            for (char bit : linea.toCharArray() ){
                if (bit == '1'){
                    this.pixelMatrix[x][y]=true;
                    System.out.print("*");
                } else {
                    this.pixelMatrix[x][y]=false;
                    System.out.print(" ");
                }
                x++;
                if (x>this.STD_WIDTH){
                    System.out.println("Error, el sprite se sale por un lado y hay que corregirlo");
                }
            }
            System.out.println();
            y++;
        }
    }

} //End of class Chip8Display
