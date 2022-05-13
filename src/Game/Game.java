package Game;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * @author sanmu
 * @version 1.0
 */
public class Game extends JFrame{
    private int row;
    private int column;
    private Cell[][] cells;
    private Cell[][] nextcells;
    private JPanel panel;
    public Game(int row,int column){
        cells=new Cell[row][column];
        nextcells=new Cell[row][column];
        this.row=row;
        this.column=column;
    }
    public void init() {
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 1000);
        this.setTitle("Game of Life");
        this.setResizable(false);
        JMenuBar menu=new JMenuBar();
        this.setJMenuBar(menu);
        JMenu options =new JMenu("预设");
        JMenu yunxing =new JMenu("运行");

        menu.add(options);
        menu.add(yunxing);


        JMenuItem radom=options.add("随机");
        JMenuItem square=options.add("矩形");
        JMenuItem kaishi=yunxing.add("开始");

        panel=new JPanel();
        panel.setLayout(new GridLayout(this.row,this.column,0,0));
        for(int i=0;i< this.row;i++){
            for (int j = 0; j < this.column ; j++) {
                JLabel jLabel = new JLabel();
                jLabel.setOpaque(true);
                jLabel.setBackground(Color.white);
                panel.add(jLabel);
            }
        }
        this.add(panel);
        radom.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //随机测试案例
                Random rand = new Random();
                for (int i = 0; i <row ; i++) {
                    for (int j = 0; j < column; j++) {
                        if(rand.nextInt(2)==1){
                            cells[i][j]=new Cell(true,i,j);
                            nextcells[i][j]=new Cell(true,i,j);
                        }else{
                            cells[i][j]=new Cell(false,i,j);
                            nextcells[i][j]=new Cell(false,i,j);
                        }
                    }
                }
                showgame();
            }
        });
        square.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //方形测试案例
                for (int i = 0; i <row ; i++) {
                    for (int j = 0; j <column; j++) {
                        cells[i][j]=new Cell(false,i,j);
                        nextcells[i][j]=new Cell(false,i,j);
                    }
                }
                for (int i = 20; i <35 ; i++) {
                    for (int j = 20; j <35; j++) {
                        cells[i][j]=new Cell(true,i,j);
                    }
                }
                for (int i = 20; i <35 ; i++) {
                    for (int j = 20; j <35; j++) {
                        nextcells[i][j]=new Cell(true,i,j);
                    }
                }
//                cells[20][20]=new Cell(true,20,20);
//                cells[20][21]=new Cell(true,20,21);
//                cells[20][22]=new Cell(true,20,22);
//                cells[20][23]=new Cell(true,20,23);
//                cells[21][20]=new Cell(true,21,20);
//                cells[21][21]=new Cell(true,21,21);
//                cells[21][22]=new Cell(true,21,22);
//                cells[21][23]=new Cell(true,21,23);
//                cells[22][20]=new Cell(true,22,20);
//                cells[22][21]=new Cell(true,22,21);
//                cells[22][22]=new Cell(true,22,22);
//                cells[22][23]=new Cell(true,22,23);
//                cells[23][20]=new Cell(true,23,20);
//                cells[23][21]=new Cell(true,23,21);
//                cells[23][22]=new Cell(true,23,22);
//                cells[23][23]=new Cell(true,23,23);
//
//                nextcells[20][20]=new Cell(true,20,20);
//                nextcells[20][21]=new Cell(true,20,21);
//                nextcells[20][22]=new Cell(true,20,22);
//                nextcells[20][23]=new Cell(true,20,23);
//                nextcells[21][20]=new Cell(true,21,20);
//                nextcells[21][21]=new Cell(true,21,21);
//                nextcells[21][22]=new Cell(true,21,22);
//                nextcells[21][23]=new Cell(true,21,23);
//                nextcells[22][20]=new Cell(true,22,20);
//                nextcells[22][21]=new Cell(true,22,21);
//                nextcells[22][22]=new Cell(true,22,22);
//                nextcells[22][23]=new Cell(true,22,23);
//                nextcells[23][20]=new Cell(true,23,20);
//                nextcells[23][21]=new Cell(true,23,21);
//                nextcells[23][22]=new Cell(true,23,22);
//                nextcells[23][23]=new Cell(true,23,23);
                showgame();
            }
        });
        kaishi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread(){
                    @Override
                    public void run() {
                        super.run();
                        rungame();
                    }
                }.start();
            }
        });
        this.setVisible(true);
    }
    public void showgame(){
        Component[] components=panel.getComponents();
        JLabel label=null;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                label=(JLabel)components[i*row+j];
                if(cells[i][j].isAlive()){
                    label.setBackground(Color.black);
                }
                else{
                    label.setBackground(Color.white);
                }
            }
        }
        panel.repaint();
    }
    public void rungame(){
        while(true){
            for(int i=0;i<row;i++){
                for(int j=0;j<column;j++){
                    if(cells[i][j].judge_alive(cells)){
                        nextcells[i][j].setAlive(true);
                    }
                    else{
                        nextcells[i][j].setAlive(false);
                    }
                }
            }
            for(int i=0;i<row;i++){
                for(int j=0;j<column;j++){
                    cells[i][j].setX(nextcells[i][j].getX());
                    cells[i][j].setY(nextcells[i][j].getY());
                    cells[i][j].setAlive(nextcells[i][j].isAlive());
                }
            }
            showgame();
            try {
                Thread.sleep( 500 );
            } catch (Exception e){
                System.exit( 0 ); //退出程序
            }
        }
    }

}





//    public void run(){
//        while(true){
//            for(int i=0;i<row;i++){
//                for(int j=0;j<column;j++){
//                    if(cells[i][j].judge_alive(cells)){
//                        nextcells[i][j].setAlive(true);
//                    }
//                    else{
//                        nextcells[i][j].setAlive(false);
//                    }
//                }
//            }
//            for(int i=0;i<row;i++){
//                for(int j=0;j<column;j++){
//                    cells[i][j].setX(nextcells[i][j].getX());
//                    cells[i][j].setY(nextcells[i][j].getY());
//                    cells[i][j].setAlive(nextcells[i][j].isAlive());
//                }
//            }
//            showgame();
//            try {
//                Thread.sleep( 1000 );
//            } catch (Exception e){
//                System.exit( 0 ); //退出程序
//            }
//        }
//    }

