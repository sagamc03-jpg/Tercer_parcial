import javax.swing.BoxLayout;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.WindowConstants;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.BorderLayout;
import java.awt.Dimension;
import datechooser.beans.DateChooserCombo;
import servicios.CambioClimaServico;

public class FrmTemperaturas extends JFrame {

    private JComboBox cmbCiudad;
    private DateChooserCombo dccDesde, dccHasta, dccDato;
    private JTabbedPane tpCambiosClima;
    private JPanel pnlGraficar, pnlRangoFechas, pnlFechaEspecifica;
    private JPanel pnlOscilacion, pnlClimaGeneral;

    public FrmTemperaturas() {
        setTitle("Cambio Climático");
        setSize(700, 400);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JToolBar tb = new JToolBar();
        JButton btnGraficar = new JButton();
        btnGraficar.setIcon(new ImageIcon(getClass().getResource("/iconos/grafico.png")));
        btnGraficar.setToolTipText("Grafica Cambios vs Fecha");
        btnGraficar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnGraficarClick();
            }
        });
        tb.add(btnGraficar);

        JButton btnDatos = new JButton();
        btnDatos.setIcon(new ImageIcon(getClass().getResource("/iconos/datosC.png")));
        btnDatos.setToolTipText("Variación climática");
        btnDatos.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent evt) {
                btnDatosClick();
            }
        });
        tb.add(btnDatos);

        // 1er pnl Grafica
        JPanel pnlCambios = new JPanel();
        pnlCambios.setLayout(new BoxLayout(pnlCambios, BoxLayout.Y_AXIS));

        pnlRangoFechas = new JPanel();
        pnlRangoFechas.setPreferredSize(new Dimension(pnlRangoFechas.getWidth(), 50)); // Altura fija de 100px
        pnlRangoFechas.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        pnlRangoFechas.setLayout(null);

        JLabel lblCiudad = new JLabel("Ciudad: ");
        lblCiudad.setBounds(40, 10, 100, 25);
        pnlRangoFechas.add(lblCiudad);

        cmbCiudad = new JComboBox();
        cmbCiudad.setBounds(100, 10, 100, 25);
        pnlRangoFechas.add(cmbCiudad);

        dccDesde = new DateChooserCombo();
        dccDesde.setBounds(330, 10, 100, 25);
        pnlRangoFechas.add(dccDesde);

        dccHasta = new DateChooserCombo();
        dccHasta.setBounds(220, 10, 100, 25);
        pnlRangoFechas.add(dccHasta);

        pnlGraficar = new JPanel();
        JScrollPane spGraficar = new JScrollPane(pnlGraficar);

        pnlCambios.add(pnlRangoFechas);
        pnlCambios.add(spGraficar);

        // 2do pnl climas en general
        pnlClimaGeneral = new JPanel();
        pnlClimaGeneral.setLayout(new BoxLayout(pnlClimaGeneral, BoxLayout.Y_AXIS));

        pnlFechaEspecifica = new JPanel();
        pnlFechaEspecifica.setPreferredSize(new Dimension(pnlFechaEspecifica.getWidth(), 50)); // Altura fija de 100px
        pnlFechaEspecifica.setMaximumSize(new Dimension(Integer.MAX_VALUE, 50));
        pnlFechaEspecifica.setLayout(null);

        JLabel lblFecha = new JLabel("Fecha: ");
        lblFecha.setBounds(40, 10, 100, 25);
        pnlFechaEspecifica.add(lblFecha);

        dccDato = new DateChooserCombo();
        dccDato.setBounds(100, 10, 100, 25);
        pnlFechaEspecifica.add(dccDato);

        pnlOscilacion = new JPanel();

        pnlClimaGeneral.add(pnlFechaEspecifica);
        pnlClimaGeneral.add(pnlOscilacion);

        // Componentes
        tpCambiosClima = new JTabbedPane();
        tpCambiosClima.addTab("Gráfica", pnlCambios);
        tpCambiosClima.addTab("Climas", pnlClimaGeneral);

        getContentPane().add(tb, BorderLayout.NORTH);
        getContentPane().add(tpCambiosClima, BorderLayout.CENTER);

        cargarDatos();
    }

    private void cargarDatos() {
        String nombreArchivo = System.getProperty("user.dir") + "/datos/Temperaturas.csv";
        var registros = CambioClimaServico.getDatos(nombreArchivo);
        var ciudad = CambioClimaServico.getCiudad(registros);

        DefaultComboBoxModel modeloCiudad = new DefaultComboBoxModel(ciudad.toArray());
        cmbCiudad.setModel(modeloCiudad);


    }

    private void btnGraficarClick() {
        tpCambiosClima.setSelectedIndex(0);

    }

    private void btnDatosClick() {
        tpCambiosClima.setSelectedIndex(1);

    }
}
