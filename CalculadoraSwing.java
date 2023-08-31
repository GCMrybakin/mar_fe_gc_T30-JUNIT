import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CalculadoraSwing extends JFrame implements ActionListener {
    private JTextField campoDisplay;
    private JButton[] botonesNumeros;
    private JButton botonSumar, botonRestar, botonMultiplicar, botonDividir, botonIgual, botonLimpiar;

    double primerOperando;
    double segundoOperando;
    char operador;

    private static final Color COLOR_FONDO = new Color(40, 40, 40);
    private static final Color COLOR_BOTON = new Color(90, 90, 90);
    private static final Color COLOR_BOTON_HOVER = new Color(110, 110, 110);
    private static final Color COLOR_TEXTO_BOTON = Color.BLACK;

    public CalculadoraSwing() {
        setTitle("Calculadora JUNIT TA30");
        setSize(350, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        getContentPane().setBackground(COLOR_FONDO);

        Font fuenteBoton = new Font("Segoe UI", Font.BOLD, 20);

        campoDisplay = new JTextField();
        campoDisplay.setEditable(false);
        campoDisplay.setFont(new Font("Segoe UI", Font.BOLD, 36));
        campoDisplay.setHorizontalAlignment(JTextField.RIGHT);
        campoDisplay.setBackground(COLOR_FONDO);
        campoDisplay.setForeground(Color.WHITE);
        add(campoDisplay, BorderLayout.NORTH);

        JPanel panelBotones = new JPanel(new GridLayout(5, 4, 10, 10));
        panelBotones.setBackground(COLOR_FONDO);
        botonesNumeros = new JButton[10];

        for (int i = 0; i < 10; i++) {
            botonesNumeros[i] = new JButton(String.valueOf(i));
            botonesNumeros[i].setFont(fuenteBoton);
            botonesNumeros[i].setBackground(COLOR_BOTON);
            botonesNumeros[i].setForeground(COLOR_TEXTO_BOTON);
            botonesNumeros[i].setFocusPainted(false);
            botonesNumeros[i].addActionListener(this);
            panelBotones.add(botonesNumeros[i]);
        }

        botonSumar = crearBoton("+", fuenteBoton);
        botonRestar = crearBoton("-", fuenteBoton);
        botonMultiplicar = crearBoton("*", fuenteBoton);
        botonDividir = crearBoton("/", fuenteBoton);
        botonIgual = crearBoton("=", fuenteBoton);
        botonLimpiar = crearBoton("C", fuenteBoton);

        panelBotones.add(botonSumar);
        panelBotones.add(botonRestar);
        panelBotones.add(botonMultiplicar);
        panelBotones.add(botonDividir);
        panelBotones.add(botonIgual);
        panelBotones.add(botonLimpiar);

        add(panelBotones, BorderLayout.CENTER);
    }

    private JButton crearBoton(String texto, Font fuente) {
        JButton boton = new JButton(texto);
        boton.setFont(fuente);
        boton.setBackground(COLOR_BOTON);
        boton.setForeground(COLOR_TEXTO_BOTON);
        boton.setFocusPainted(false);
        boton.addActionListener(this);
        boton.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                boton.setBackground(COLOR_BOTON_HOVER);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                boton.setBackground(COLOR_BOTON);
            }
        });
        return boton;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object fuente = e.getSource();

        if (fuente == botonLimpiar) {
            campoDisplay.setText("");
        } else if (fuente == botonIgual) {
            segundoOperando = Double.parseDouble(campoDisplay.getText());
            double resultado = realizarCalculo();
            campoDisplay.setText(String.valueOf(resultado));
            primerOperando = resultado;
            operador = ' ';
        } else if (fuente == botonSumar || fuente == botonRestar ||
                fuente == botonMultiplicar || fuente == botonDividir) {
            primerOperando = Double.parseDouble(campoDisplay.getText());
            operador = e.getActionCommand().charAt(0);
            campoDisplay.setText("");
        } else {
            campoDisplay.setText(campoDisplay.getText() + e.getActionCommand());
        }
    }

    double realizarCalculo() {
        switch (operador) {
            case '+':
                return primerOperando + segundoOperando;
            case '-':
                return primerOperando - segundoOperando;
            case '*':
                return primerOperando * segundoOperando;
            case '/':
                if (segundoOperando != 0) {
                    return primerOperando / segundoOperando;
                } else {
                    throw new ArithmeticException("No se puede dividir por cero.");
                }
            default:
                throw new IllegalArgumentException("Operador invalido.");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }

            CalculadoraSwing calculadora = new CalculadoraSwing();
            calculadora.setVisible(true);
        });
    }
}
