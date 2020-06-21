package aerotaxi;

import javax.swing.*;

import java.awt.*;
import java.text.*;
import java.util.Date;
import java.util.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class userInterface extends JFrame{
	//constantes
	private static final long serialVersionUID = 1L;
	
	//colores 
	private Color naranja = new Color(238,132,52);
	private Color azul = new Color(72, 77, 109);
	private Color oscuro = new Color(25,19,8);
	//fuentes
	private Font fuenteHeader = new Font("arial",Font.BOLD,20);
	private Font fuenteBtn = new Font("arial",Font.PLAIN,16);
	private Font fuenteFormulario = new Font("arial",Font.PLAIN,15);
	private Font fuenteInputFormulario = new Font("arial",Font.ITALIC,15);
	private Font fuenteMensajeDelSistema = new Font("arial",Font.BOLD,12);
	
	//DATE
	private SimpleDateFormat formatoFechaAerotaxi = new SimpleDateFormat("dd/MM/yyyy");

	//atributos
	private Container contentPane; //contenido del jframe
	private ArrayList<Usuario> usuarios;
	private ArrayList <Vuelo> vuelos;
	
	//INDEX PANEL
	private JPanel indexPanel; //panel de inico
	
	//"NUEVO USUARIO" PANEL
	private JPanel nuevoUsuarioPanel; //panel para cargar nuevo usuario
	private JTextField nombreTextField;
	private JTextField apellidoTextField;
	private JTextField dniTextField;
	private JTextField nacimientoTextField;
	private JLabel advertenciaCamposVacios;
	private JLabel nuevoUsuarioCreado;
	
	//"VER USUARIOS" PANEL
	private JPanel verUsuariosPanel; //panel para ver todos los usuarios
	
	//"VER VUELOS" PANEL
	private JPanel verVuelosPanel; //panel q muestra los vuelos de una fecha dada
	private JTextField fechaDeVuelos;
	private JTextArea vuelosPorFecha;
	
	//RESERVAR VUELOS PANEL
	private JPanel reservasPanel;
	private JLabel advertenciaCamposReserva;
	private JTextField fechaReserva;
	private JTextField cantidadPasajeros;
	private JTextField costoFinal;
	private JTextField usuarioReserva;
	private JButton calcularCostoBtn;
	private JButton confirmarReservaBtn;
	private JComboBox<String> origenBox;
	private JComboBox<String> destinoBox;
	private JComboBox<String> avionesDisponibles;
	private String [] ciudadesOrigen = {"-- Seleccione una ciudad --","Buenos Aires","Córdoba","Montevideo"};
	private String [] ciudadesDestino = {"-- Seleccione una ciudad --","Córdoba","Montevideo","Santiago de Chile"};
	private HashMap<Integer,Vuelo> vuelosCompatibles = new HashMap<Integer,Vuelo>(); 
	private JLabel reservaExitosaLabel;
	
	//CANCELAR VUELOS PANEL
	private JPanel cancelacionPanel;
	private JTextField dniUsuarioCancelacion;
	private JComboBox<String> pasajesReservadosComboBox;
	private ArrayList<Pasaje> pasajesUsuarioCancelacion = new ArrayList<Pasaje>();
	private JButton btnConfirmarCancelacion;
	private JLabel mensajeCancelacionConfirmada;
	
	//constructor
	public userInterface(ArrayList<Usuario> usuarios,ArrayList<Vuelo> vuelos) {
		setTitle("AeroTaxi");
		setSize(900,600);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		setResizable(false);
		
		this.usuarios = usuarios;
		this.vuelos = vuelos;
		
		contentPane = getContentPane();
		
		//agrego panel index al jframe
		cargarIndexPanel();
	}
	
	//index JPanel y componentes
	private void cargarIndexPanel() {
		indexPanel = new JPanel();
		indexPanel.setVisible(true);
		indexPanel.setLayout(null);
		
		//fondo azul
		indexPanel.setOpaque(true);
		indexPanel.setBackground(azul);
		
		//cargo todos los componentes del Index Panel
		cargarIndexHeader();
		cargarIndexButtons();
		
		contentPane.add(indexPanel);
	}

	//header INICIO
	private void cargarIndexHeader() {
		JLabel labelInicio = new JLabel ("   Inicio");
		labelInicio.setBounds(0,0,900,50);
		labelInicio.setOpaque(true);
		labelInicio.setBackground(oscuro);
		labelInicio.setFont(fuenteHeader);
		labelInicio.setForeground(Color.white);
		
		indexPanel.add(labelInicio);
	}
	
	//botones del panel de inicio
	private void cargarIndexButtons() {
		//instanciamos 5 botones
		JButton btnNuevoUsuario = new JButton("Nuevo usuario");
		btnNuevoUsuario.setBounds(161,158,244,99);
		btnNuevoUsuario.setOpaque(true);
		btnNuevoUsuario.setBackground(naranja);
		btnNuevoUsuario.setFont(fuenteBtn);
		btnNuevoUsuario.setForeground(Color.white);
		
		JButton btnVerUsuarios = new JButton("Ver usuarios");
		btnVerUsuarios.setBounds(161,270,244,99);
		btnVerUsuarios.setOpaque(true);
		btnVerUsuarios.setBackground(naranja);
		btnVerUsuarios.setFont(fuenteBtn);
		btnVerUsuarios.setForeground(Color.white);
		
		JButton btnVerVuelos = new JButton ("Ver vuelos");
		btnVerVuelos.setBounds(495,158,244,99);
		btnVerVuelos.setOpaque(true);
		btnVerVuelos.setBackground(naranja);
		btnVerVuelos.setFont(fuenteBtn);
		btnVerVuelos.setForeground(Color.white);
		
		JButton btnReservarVuelo = new JButton("Reservar vuelo");
		btnReservarVuelo.setBounds(495,270,244,99);
		btnReservarVuelo.setOpaque(true);
		btnReservarVuelo.setBackground(naranja);
		btnReservarVuelo.setFont(fuenteBtn);
		btnReservarVuelo.setForeground(Color.white);
		
		JButton btnCancelarVuelo = new JButton("Cancelar vuelo");
		btnCancelarVuelo.setBounds(495,383,244,99);
		btnCancelarVuelo.setOpaque(true);
		btnCancelarVuelo.setBackground(naranja);
		btnCancelarVuelo.setFont(fuenteBtn);
		btnCancelarVuelo.setForeground(Color.white);
		
		indexPanel.add(btnNuevoUsuario);
		indexPanel.add(btnVerUsuarios);
		indexPanel.add(btnVerVuelos);
		indexPanel.add(btnReservarVuelo);
		indexPanel.add(btnCancelarVuelo);
		
		//agregamos eventos a los botones
		//evento del boton Nuevo Usuario
		ActionListener irNuevoUsuario = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indexPanel.setVisible(false);
				cargarNuevoUsuarioPanel();
			}
		};
		btnNuevoUsuario.addActionListener(irNuevoUsuario);
		
		//evento del boton Ver Usuarios
		ActionListener irVerUsuarios = new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent ae) {
				indexPanel.setVisible(false);
				cargarVerUsuariosPanel();
			}
		};
		btnVerUsuarios.addActionListener(irVerUsuarios);
		
		//evento del btn VerVuelos
		ActionListener irVerVuelos = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indexPanel.setVisible(false);
				cargarVerVuelosPanel();
			}
		};
		btnVerVuelos.addActionListener(irVerVuelos);
		
		//evento del btn Reservar Vuelo
		ActionListener reservasAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indexPanel.setVisible(false);
				cargarReservasPanel();
			}
		};
		btnReservarVuelo.addActionListener(reservasAction);
		
		//evento del boton Cancelar Vuelo
		ActionListener cancelarVueloAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				indexPanel.setVisible(false);
				cargarCancelarVueloPanel();
			}
		};
		btnCancelarVuelo.addActionListener(cancelarVueloAction);
		
	}
		
	
	
	
	
	//PANEL DE NUEVO USUARIO
	private void cargarNuevoUsuarioPanel() {
		nuevoUsuarioPanel = new JPanel();
		nuevoUsuarioPanel.setVisible(true);
		nuevoUsuarioPanel.setLayout(null);
		
		//fondo azul
		nuevoUsuarioPanel.setOpaque(true);
		nuevoUsuarioPanel.setBackground(azul);
		
		//cargo todos los componentes del Nuevo Usuario Panel
		cargarNuevoUsuarioHeader();
		cargarNuevoUsuarioFormLabels();
		cargarNuevoUsuarioTextFields();
		cargarNuevoUsuarioBtns();
		
		contentPane.add(nuevoUsuarioPanel);
	}
	
	//header de Nuevo Usuario
	private void cargarNuevoUsuarioHeader() {
		JLabel labelNuevoUsuario = new JLabel ("   Nuevo usuario");
		labelNuevoUsuario.setBounds(0,0,900,50);
		labelNuevoUsuario.setOpaque(true);
		labelNuevoUsuario.setBackground(oscuro);
		labelNuevoUsuario.setFont(fuenteHeader);
		labelNuevoUsuario.setForeground(Color.white);
		
		nuevoUsuarioPanel.add(labelNuevoUsuario);
	}
	
	//etiquetas de txt del formulario
	private void cargarNuevoUsuarioFormLabels() {
		//etiqueta nombre
		JLabel nombreLabel = new JLabel("Nombre");
		nombreLabel.setBounds(282,106,337,21);
		nombreLabel.setFont(fuenteFormulario);
		nombreLabel.setForeground(Color.white);
		
		//etiqueta apellido
		JLabel apellidoLabel = new JLabel("Apellido");
		apellidoLabel.setBounds(282,183,337,21);
		apellidoLabel.setFont(fuenteFormulario);
		apellidoLabel.setForeground(Color.white);

		//etiqueta dni
		JLabel dniLabel = new JLabel("DNI (sin separador de miles)");
		dniLabel.setBounds(282,260,337,21);
		dniLabel.setFont(fuenteFormulario);
		dniLabel.setForeground(Color.white);
		
		//etiqueta fecha de nacimiento
		JLabel nacimientoLabel = new JLabel("Fecha de nacimiento (dd/mm/yyyy)");
		nacimientoLabel.setBounds(282,337,337,21);
		nacimientoLabel.setFont(fuenteFormulario);
		nacimientoLabel.setForeground(Color.white);
		
		//etiqueta de advertencia de campos vacios
		advertenciaCamposVacios = new JLabel("Por favor complete todos los campos*");
		advertenciaCamposVacios.setVisible(false);
		advertenciaCamposVacios.setBounds(445,405,220,18);
		advertenciaCamposVacios.setFont(fuenteMensajeDelSistema);
		advertenciaCamposVacios.setForeground(Color.red);
		
		//etiqueta de nuevo usuario generado con exito
		nuevoUsuarioCreado = new JLabel("Nuevo usuario creado con éxito*");
		nuevoUsuarioCreado.setVisible(false);
		nuevoUsuarioCreado.setBounds(463,405,220,18);
		nuevoUsuarioCreado.setFont(fuenteMensajeDelSistema);
		nuevoUsuarioCreado.setForeground(Color.green);
		
		
		//agrego todas la panel Nuevo Usuario
		nuevoUsuarioPanel.add(nombreLabel);
		nuevoUsuarioPanel.add(apellidoLabel);
		nuevoUsuarioPanel.add(dniLabel);
		nuevoUsuarioPanel.add(nacimientoLabel);
		//agrego mensajes del sistema
		nuevoUsuarioPanel.add(advertenciaCamposVacios);
		nuevoUsuarioPanel.add(nuevoUsuarioCreado);
	}
	
	//campos de texto del formulario para el NUevo Usuario
	private void cargarNuevoUsuarioTextFields() {
		//etiqueta nombre
		nombreTextField = new JTextField();
		nombreTextField.setBounds(282,128,337,26);
		nombreTextField.setFont(fuenteInputFormulario);
		
		//etiqueta apellido
		apellidoTextField = new JTextField();
		apellidoTextField.setBounds(282,205,337,26);
		apellidoTextField.setFont(fuenteInputFormulario);
		
		//etiqueta dni
		dniTextField = new JTextField();
		dniTextField.setBounds(282,282,337,26);
		dniTextField.setFont(fuenteInputFormulario);
		
		//etiqueta fecha de nacimiento
		nacimientoTextField = new JTextField();
		nacimientoTextField.setBounds(282,359,337,26);
		nacimientoTextField.setFont(fuenteInputFormulario);
		
		nuevoUsuarioPanel.add(nombreTextField);
		nuevoUsuarioPanel.add(apellidoTextField);
		nuevoUsuarioPanel.add(dniTextField);
		nuevoUsuarioPanel.add(nacimientoTextField);
		
		//eventos Focus para campo nombre
		FocusListener nombreFocus = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				nombreTextField.setText("");
				nuevoUsuarioCreado.setVisible(false);
				advertenciaCamposVacios.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		};
		nombreTextField.addFocusListener(nombreFocus);
		
		//eventos Focus para campo apellido
		FocusListener apellidoFocus = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				apellidoTextField.setText("");
				nuevoUsuarioCreado.setVisible(false);
				advertenciaCamposVacios.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		};
		apellidoTextField.addFocusListener(apellidoFocus);
		
		//eventos Focus para campo dni
		FocusListener dniFocus = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				dniTextField.setText("");
				nuevoUsuarioCreado.setVisible(false);
				advertenciaCamposVacios.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		};
		dniTextField.addFocusListener(dniFocus);
		
		//eventos Focus para campo fecha de nacimiento
		FocusListener nacimientoFocus = new FocusListener() {
			@Override
			public void focusGained(FocusEvent e) {
				nacimientoTextField.setText("");
				nuevoUsuarioCreado.setVisible(false);
				advertenciaCamposVacios.setVisible(false);
			}
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
		};
		nacimientoTextField.addFocusListener(nacimientoFocus);
		
	}
	
	//botones de confirmar y volver al panel de inicio
	private void cargarNuevoUsuarioBtns() {
		JButton btnVolver= new JButton("Volver");
		btnVolver.setBounds(282,427,134,51);
		btnVolver.setOpaque(true);
		btnVolver.setBackground(naranja);
		btnVolver.setFont(fuenteBtn);
		btnVolver.setForeground(Color.white);
		
		JButton btnConfirmar = new JButton("Confirmar");
		btnConfirmar.setBounds(485,427,134,51);
		btnConfirmar.setOpaque(true);
		btnConfirmar.setBackground(naranja);
		btnConfirmar.setFont(fuenteBtn);
		btnConfirmar.setForeground(Color.white);
		
		nuevoUsuarioPanel.add(btnVolver);
		nuevoUsuarioPanel.add(btnConfirmar);
		
		//agrego eventos al btn VOLVER
		ActionListener actionVolver = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				//limpio todos los campos de texto del formulario
				nombreTextField.setText("");
				apellidoTextField.setText("");
				dniTextField.setText("");
				nacimientoTextField.setText("");
				nuevoUsuarioPanel.setVisible(false);
				indexPanel.setVisible(true);
			}
		};
		btnVolver.addActionListener(actionVolver);
		
		//agrego evento al btn CONFIRMAR
		ActionListener actionConfirmar = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent ae) {
				try {
					//chequeo q todos los campos tengna algo escrito
					if(!campoVacio(nombreTextField) && !campoVacio(apellidoTextField) && !campoVacio(dniTextField) && !campoVacio(nacimientoTextField)) {
						//chequeo el formato de la fecha -- arroja ParseException si el formatno no es dd/MM/yyyy
						checkFechaInput(nacimientoTextField.getText());
						if(!dniExiste(dniTextField.getText())) {
							//agrego nuevo usuario a la coleccion de usuarios
							agregarNuevoUsuario(nombreTextField.getText(),apellidoTextField.getText(),dniTextField.getText(),nacimientoTextField.getText());
							//limpio todos los campos de texto del formulario
							nombreTextField.setText("");
							apellidoTextField.setText("");
							dniTextField.setText("");
							nacimientoTextField.setText("");
							//muestro mensaje de exito
							nuevoUsuarioCreado.setVisible(true);
						}else {
							dniTextField.setText("*El DNI ingresado ya pertenece a un usuario*");
						}
					}else { //si falta completar algun campo muestro mensaje de advertencia
						advertenciaCamposVacios.setVisible(true);
					}
				}catch (ParseException pe) {
					//si se captura la excepcion del formato de fecha ingresado => muestro advertencia
					nacimientoTextField.setText("*Respete el formato indicado*");
				}
			}
		};
		btnConfirmar.addActionListener(actionConfirmar);
	}
	
	//controla q un JTextField este vacio
	private boolean campoVacio(JTextField campo) {
		if(campo.getText().equals(""))
			return true;
		else
			return false;
	}
	
	//chequea el formato de una fecha (dd/MM/yyyy) pasada como String por parametro
	private void checkFechaInput(String fechaString) throws ParseException {
		Date fechaInput = formatoFechaAerotaxi.parse(fechaString);
		if(!formatoFechaAerotaxi.format(fechaInput).equals(fechaString))
			throw new ParseException("Formato de fecha invalido",0);
	}
	
	//chequea q ya exista un usuario con el dni pasado por parametro
	private boolean dniExiste(String dni) {
		boolean existe = false;
		for(Usuario usuario : usuarios) {
			if(usuario.getDni().equals(dni)) existe = true;
		}
		return existe;
	}
	
	//agrega un nuevo ususario a la coleccion del programa
	private void agregarNuevoUsuario(String nombre,String apellido,String dni,String nacimiento) throws ParseException{
		Date nacimientoDate = formatoFechaAerotaxi.parse(nacimiento); //nunca va a arrojar la ParsException ya q se va a haber chequeado la fecha en el actionConfirmar del btnConfirmar
		usuarios.add(new Usuario(nombre,apellido,dni,nacimientoDate));
	}
	
	
	
	
	//PANEL DE VER USUARIOS
	private void cargarVerUsuariosPanel() {
		verUsuariosPanel = new JPanel();
		verUsuariosPanel.setVisible(true);
		verUsuariosPanel.setLayout(null);
		
		//fondo azul
		verUsuariosPanel.setOpaque(true);
		verUsuariosPanel.setBackground(azul);
		
		//cargo componentes del panel Ver Usuarios
		cargarVerUsuariosHeader();
		cargarVerUsuariosTextArea(); //aca estaran visibles todos los usuarios
		cargarVerUsuariosBtn();
		
		contentPane.add(verUsuariosPanel);
	}
	
	private void cargarVerUsuariosHeader() {
		JLabel labelVerUsuarios = new JLabel ("   Usuarios");
		labelVerUsuarios.setBounds(0,0,900,50);
		labelVerUsuarios.setOpaque(true);
		labelVerUsuarios.setBackground(oscuro);
		labelVerUsuarios.setFont(fuenteHeader);
		labelVerUsuarios.setForeground(Color.white);
		
		verUsuariosPanel.add(labelVerUsuarios);
	}
	
	private void cargarVerUsuariosTextArea() {
		JTextArea usuariosArea = new JTextArea();
		usuariosArea.setEditable(false);
		usuariosArea.setFont(fuenteMensajeDelSistema);
		usuariosArea.setText(mostrarUsuarios());
		
		JScrollPane scrollPane = new JScrollPane(usuariosArea);
		scrollPane.setBounds(161,100,578,325);
		
		verUsuariosPanel.add(scrollPane);
	}
	
	private String mostrarUsuarios() {
		String listaDeUsuarios = "";
		double gastosTotales;
		int mayorTarifaPagada;
		if(usuarios.size() > 0) {
			for(Usuario u : usuarios) {
				gastosTotales = 0;
				mayorTarifaPagada = 0;
				for(Pasaje p : u.getPasajes()) {
					if(p.getReservado() == true) {
						gastosTotales += p.getVuelo().calcularCostoDeVuelo(p.getCantidadDeViajeros());
						if(mayorTarifaPagada < p.getVuelo().getAvion().getTarifaFija()) {
							mayorTarifaPagada = p.getVuelo().getAvion().getTarifaFija();
						}
					}
				}
				listaDeUsuarios += u.toString() + String.format("\nGastos totales: $%.2f",gastosTotales);
				switch(mayorTarifaPagada) {
				case 6000:
					listaDeUsuarios += "\nMejor avion: GOLD\n\n";
					break;
				case 4000:
					listaDeUsuarios += "\nMejor avion: SILVER\n\n";
					break;
				case 3000:
					listaDeUsuarios += "\nMejor avion: BRONZE\n\n";
					break;
				default:
					listaDeUsuarios += "\nAUN NO HA REALIZADO NINGUN VIAJE\n\n";
					break;
				}
			}
		}else {
			listaDeUsuarios = "Aún no hay usuarios registrados en el sistema";
		}
		
		return listaDeUsuarios;
	}
	
	private void cargarVerUsuariosBtn() {
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(383,450,134,51);
		btnVolver.setOpaque(true);
		btnVolver.setBackground(naranja);
		btnVolver.setFont(fuenteBtn);
		btnVolver.setForeground(Color.white);
		
		verUsuariosPanel.add(btnVolver);
		
		ActionListener volverActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verUsuariosPanel.setVisible(false);
				indexPanel.setVisible(true);
			}
		};
		btnVolver.addActionListener(volverActionListener);
	}





	//PANEL DE VER VUELOS
	private void cargarVerVuelosPanel() {
		verVuelosPanel = new JPanel();
		verVuelosPanel.setVisible(true);
		verVuelosPanel.setLayout(null);
		
		//fondo azul
		verVuelosPanel.setOpaque(true);
		verVuelosPanel.setBackground(azul);
		
		//cargo componentes del panel de Ver Vuelos
		cargarVerVuelosHeader();
		cargarVerVuelosLabels();
		cargarVerVuelosTextFields();
		cargarVerVuelosTextArea();
		cargarVerVuelosBtn();
		
		contentPane.add(verVuelosPanel);
	}

	private void cargarVerVuelosHeader() {
		JLabel labelVuelos = new JLabel ("   Vuelos");
		labelVuelos.setBounds(0,0,900,50);
		labelVuelos.setOpaque(true);
		labelVuelos.setBackground(oscuro);
		labelVuelos.setFont(fuenteHeader);
		labelVuelos.setForeground(Color.white);
		
		verVuelosPanel.add(labelVuelos);
	}
	
	private void cargarVerVuelosLabels() {
		JLabel indiqueFechaDeVuelos = new JLabel("Indique una fecha (dd/mm/yyyy)");
		indiqueFechaDeVuelos.setBounds(161,86,458,17);
		indiqueFechaDeVuelos.setFont(fuenteFormulario);
		indiqueFechaDeVuelos.setForeground(Color.white);
		
		verVuelosPanel.add(indiqueFechaDeVuelos);
	}
	
	private void cargarVerVuelosTextFields() {
		fechaDeVuelos = new JTextField();
		fechaDeVuelos.setBounds(161,103,458,30);
		fechaDeVuelos.setFont(fuenteInputFormulario);
		
		verVuelosPanel.add(fechaDeVuelos);
		
		FocusListener buscadorListener = new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void focusGained(FocusEvent e) {
				fechaDeVuelos.setText("");
			}
		};
		fechaDeVuelos.addFocusListener(buscadorListener);
	}

	private void cargarVerVuelosTextArea() {
		vuelosPorFecha = new JTextArea();
		vuelosPorFecha.setFont(fuenteMensajeDelSistema);
		vuelosPorFecha.setEditable(false);
		//cargo scroll pane
		JScrollPane spVuelos = new JScrollPane(vuelosPorFecha);
		spVuelos.setBounds(161,158,578,270);
		
		verVuelosPanel.add(spVuelos);
	}
	
	private void cargarVerVuelosBtn() {
		JButton btnBuscarVuelos = new JButton("Buscar");
		btnBuscarVuelos.setBounds(625,103,114,30);
		btnBuscarVuelos.setOpaque(true);
		btnBuscarVuelos.setBackground(naranja);
		btnBuscarVuelos.setFont(fuenteBtn);
		btnBuscarVuelos.setForeground(Color.white);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.setBounds(383,450,134,51);
		btnVolver.setOpaque(true);
		btnVolver.setBackground(naranja);
		btnVolver.setFont(fuenteBtn);
		btnVolver.setForeground(Color.white);
		
		verVuelosPanel.add(btnBuscarVuelos);
		verVuelosPanel.add(btnVolver);
		
		ActionListener buscarActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					if(!campoVacio(fechaDeVuelos)) {
						checkFechaInput(fechaDeVuelos.getText());
						vuelosPorFecha.setText(verVuelosPorFecha(fechaDeVuelos.getText()));
					}
				}catch(ParseException pe) {
					fechaDeVuelos.setText("*Respete el formato indicado*");
					System.out.println("Excepcion por fecha mal ingresada");
					
				}
			}
		};
		btnBuscarVuelos.addActionListener(buscarActionListener);
		
		ActionListener volverActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				verVuelosPanel.setVisible(false);
				indexPanel.setVisible(true);
			}
		};
		btnVolver.addActionListener(volverActionListener);
	}
	
	private String verVuelosPorFecha(String fecha) throws ParseException {
		String listaDevuelos = "";
		for(Vuelo v : vuelos) {
			if(v.getFecha().equals(formatoFechaAerotaxi.parse(fecha))) {
				listaDevuelos += v.toString()+"\n\n";
			}
		}
		if(listaDevuelos.equals("")) listaDevuelos += "NO HAY VUELOS REGISTRADOS PARA ESTA FECHA";
		
		return listaDevuelos;
	}

	
	
	
	//PANEL DE RESERVAR VUELOS
	private void cargarReservasPanel() {
		reservasPanel = new JPanel();
		reservasPanel.setVisible(true);
		reservasPanel.setLayout(null);
		reservasPanel.setOpaque(true);
		reservasPanel.setBackground(azul);
		
		//cargo todos los componentes del panel
		cargarReservasHeader();
		cargarReservasLabels();
		cargarReservasTextField();
		cargarReservasComoboBox();
		cargarReservasBtn();
		
		contentPane.add(reservasPanel);
	}
	
	private void cargarReservasHeader() {
		JLabel labelReservas= new JLabel ("   Reservas");
		labelReservas.setBounds(0,0,900,50);
		labelReservas.setOpaque(true);
		labelReservas.setBackground(oscuro);
		labelReservas.setFont(fuenteHeader);
		labelReservas.setForeground(Color.white);
		
		reservasPanel.add(labelReservas);
	}
	
	private void cargarReservasLabels() {
		JLabel fechaVuelo = new JLabel("Indique una fecha (dd/mm/yyyy)");
		fechaVuelo.setBounds(161,86,458,17);
		fechaVuelo.setFont(fuenteFormulario);
		fechaVuelo.setForeground(Color.white);
		
		JLabel origen = new JLabel("Origen");
		origen.setBounds(161,140,458,17);
		origen.setFont(fuenteFormulario);
		origen.setForeground(Color.white);
		
		
		JLabel destino = new JLabel("Destino");
		destino.setBounds(485,140,100,17);
		destino.setFont(fuenteFormulario);
		destino.setForeground(Color.white);
		
		JLabel cantidadPasajeros = new JLabel("Cantidad de pasajeros");
		cantidadPasajeros.setBounds(161,195,458,17);
		cantidadPasajeros.setFont(fuenteFormulario);
		cantidadPasajeros.setForeground(Color.white);
		
		JLabel avionesDisponibles = new JLabel("Aviones disponibles");
		avionesDisponibles.setBounds(161,272,458,17);
		avionesDisponibles.setFont(fuenteFormulario);
		avionesDisponibles.setForeground(Color.white);
		
		JLabel costoFinal = new JLabel("Costo final");
		costoFinal.setBounds(161,328,458,17);
		costoFinal.setFont(fuenteFormulario);
		costoFinal.setForeground(Color.white);
		
		JLabel usuarioDniReserva = new JLabel("DNI del usuario (sin separador de miles)");
		usuarioDniReserva.setBounds(161,384,458,17);
		usuarioDniReserva.setFont(fuenteFormulario);
		usuarioDniReserva.setForeground(Color.white);
		
		advertenciaCamposReserva = new JLabel("Por favor corrobore todos los campos*");
		advertenciaCamposReserva.setBounds(503,195,256,18);
		advertenciaCamposReserva.setFont(fuenteMensajeDelSistema);
		advertenciaCamposReserva.setForeground(Color.red);
		advertenciaCamposReserva.setVisible(false);
		
		reservaExitosaLabel = new JLabel("Reserva exitosa*");
		reservaExitosaLabel.setBounds(572,452,200,18);
		reservaExitosaLabel.setFont(fuenteMensajeDelSistema);
		reservaExitosaLabel.setForeground(Color.green);
		reservaExitosaLabel.setVisible(false);
		
		reservasPanel.add(fechaVuelo);
		reservasPanel.add(origen);
		reservasPanel.add(destino);
		reservasPanel.add(cantidadPasajeros);
		reservasPanel.add(avionesDisponibles);
		reservasPanel.add(costoFinal);
		reservasPanel.add(usuarioDniReserva);
		reservasPanel.add(advertenciaCamposReserva);
		reservasPanel.add(reservaExitosaLabel);
	}
	
	private void cargarReservasBtn() {
		JButton buscarVuelosBtn = new JButton("Buscar vuelos");
		buscarVuelosBtn.setBounds(485,214,256,30);
		buscarVuelosBtn.setOpaque(true);
		buscarVuelosBtn.setBackground(naranja);
		buscarVuelosBtn.setFont(fuenteBtn);
		buscarVuelosBtn.setForeground(Color.white);
		
		calcularCostoBtn = new JButton("Calcular costo");
		calcularCostoBtn.setBounds(383,473,134,51);
		calcularCostoBtn.setEnabled(false);
		calcularCostoBtn.setOpaque(true);
		calcularCostoBtn.setBackground(naranja);
		calcularCostoBtn.setFont(fuenteBtn);
		calcularCostoBtn.setForeground(Color.white);
		
		confirmarReservaBtn = new JButton("Confirmar");
		confirmarReservaBtn.setBounds(552,473,134,51);
		confirmarReservaBtn.setEnabled(false);
		confirmarReservaBtn.setOpaque(true);
		confirmarReservaBtn.setBackground(naranja);
		confirmarReservaBtn.setFont(fuenteBtn);
		confirmarReservaBtn.setForeground(Color.white);
		
		JButton volverBtn = new JButton("Volver");
		volverBtn.setBounds(216,473,134,51);
		volverBtn.setOpaque(true);
		volverBtn.setBackground(naranja);
		volverBtn.setFont(fuenteBtn);
		volverBtn.setForeground(Color.white);
		
		reservasPanel.add(buscarVuelosBtn);
		reservasPanel.add(calcularCostoBtn);
		reservasPanel.add(confirmarReservaBtn);
		reservasPanel.add(volverBtn);
	
		
//		HashMap<Integer,Vuelo> vuelosCompatibles = new HashMap<Integer,Vuelo>(); 
		
		//evento del btn Volver
		ActionListener volverBtnAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				reservasPanel.setVisible(false);
				indexPanel.setVisible(true);
			}
		};
		volverBtn.addActionListener(volverBtnAction);
		
		//evento del btn Buscar Vuelos
		ActionListener buscarVuelosAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					//corroboro q esten completos todos los campos, q la ruta sea valida y q la cantidad de pasajeros sea un valor entero positvo
					if(!campoVacio(fechaReserva) && !campoVacio(cantidadPasajeros) && checkRutaReserva(origenBox.getSelectedIndex(), destinoBox.getSelectedIndex()) && checkCantidadPasajerosInput(cantidadPasajeros.getText())){
						//chequeo el formato de la fecha (dd/MM/yyyy) -- arroja ParseException si no es correcta
						checkFechaInput(fechaReserva.getText());
						//remuevo todos los items de la busqueda anterior
						avionesDisponibles.removeAllItems();
						//activo el combo box
						avionesDisponibles.setEnabled(true);

						//vacio el hashmap por si hubo una busqueda previa
						vuelosCompatibles.clear();
						//busco los vuelos compatibles y los almaceno en el HM<Integer,Vuelo> vuelosCompatibles
						vuelosCompatibles = buscarVuelosCompatibles(formatoFechaAerotaxi.parse(fechaReserva.getText()), ciudadesOrigen[origenBox.getSelectedIndex()], ciudadesDestino[destinoBox.getSelectedIndex()], Integer.parseInt(cantidadPasajeros.getText()));
						//si hay al menos 1 vuelo compatible => lo muestro en el combo box
						//y activo el boton de Calcular Costo
						if(vuelosCompatibles.size() > 0) {
							for(Map.Entry<Integer,Vuelo> vueloCompatible : vuelosCompatibles.entrySet()) {
								avionesDisponibles.addItem(vueloCompatible.getValue().toString());
							}
							calcularCostoBtn.setEnabled(true);
						}else {
							avionesDisponibles.addItem("-- No hay aviones disponibles --");
							calcularCostoBtn.setEnabled(false);
							confirmarReservaBtn.setEnabled(false);
						}
					}else {
						advertenciaCamposReserva.setVisible(true);
					}	 
				}catch(ParseException pe) {
					if(!fechaReserva.getText().equals(""))
						fechaReserva.setText("*Respete el formato indicado*");
				}catch(NumberFormatException nfe){
					if(!cantidadPasajeros.getText().equals(""))
						cantidadPasajeros.setText("*Ingrese un numero entero*");
				}
			}
		};
		buscarVuelosBtn.addActionListener(buscarVuelosAction);
		
		//evento del btn Calcular Costo
		ActionListener calcularCostoAction = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				costoFinal.setText(String.format("$ %.2f",vuelosCompatibles.get(avionesDisponibles.getSelectedIndex()+1).calcularCostoDeVuelo(Integer.parseInt(cantidadPasajeros.getText()))));
				confirmarReservaBtn.setEnabled(true);
				usuarioReserva.setEnabled(true);
			}
		};
		calcularCostoBtn.addActionListener(calcularCostoAction);
		
		//evento del btn Confirmar Reserva
		ActionListener confirmarReservActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//chequeo q haya un dni del usuario q va a hacer la reserva
				if(!campoVacio(usuarioReserva)){
					//chequeo q el dni ingresado corresponda a un usuario registrado
					if(dniExiste(usuarioReserva.getText())) {
						//busco (entre los vuelos compatibles con la busqueda) el vuelo q esta seleccionado en el combobox
						Vuelo vueloCofiramdo = vuelosCompatibles.get(avionesDisponibles.getSelectedIndex() + 1);
						//busco entre todos los vuelos el vuelo del cual se reservaron asientos
						for(Vuelo v : vuelos) {
							if(v.equals(vueloCofiramdo)) {
								//disminuyo la cantidad de asientos disponibles del vuelo
								v.setAsientosLibres(v.getAsientosLibres()-Integer.parseInt(cantidadPasajeros.getText()));
								//busco entre todos los usuarios el usuario con el dni indicado de quien hizo la reserva
								for(Usuario u : usuarios) {
									if(u.getDni().equals(usuarioReserva.getText()))
										u.agregarPasaje(v, Integer.parseInt(cantidadPasajeros.getText()));
								}
							}
						}
						//muestro mensaje de exito de reserva;
						reservaExitosaLabel.setVisible(true);
						//desactivo los botnes de calcular costo y confirmar
						calcularCostoBtn.setEnabled(false);
						confirmarReservaBtn.setEnabled(false);
						
						//limpio todos los campos del formulario
						fechaReserva.setText("");
						origenBox.setSelectedIndex(0);
						destinoBox.setSelectedIndex(0);
						avionesDisponibles.setSelectedIndex(0);
						avionesDisponibles.removeAllItems();
						avionesDisponibles.setEnabled(false);
						costoFinal.setText("");
						cantidadPasajeros.setText("");
						usuarioReserva.setText("");
						usuarioReserva.setEnabled(false);
					}else {
						usuarioReserva.setText("*El DNI ingresado no corresponde a un usuario registrado*");
					}
				}else {
					usuarioReserva.setText("*Complete este campo*");
				}
			}
		};
		confirmarReservaBtn.addActionListener(confirmarReservActionListener);
		
	}
	
	private void cargarReservasTextField() {
		fechaReserva = new JTextField();
		fechaReserva.setBounds(161,103,578,30);
		fechaReserva.setFont(fuenteInputFormulario);
		
		cantidadPasajeros = new JTextField();
		cantidadPasajeros.setBounds(161,214,254,30);
		cantidadPasajeros.setFont(fuenteInputFormulario);
		
		costoFinal = new JTextField();
		costoFinal.setBounds(161,346,578,30);
		costoFinal.setEditable(false);
		costoFinal.setFont(new Font("arial",Font.BOLD,18));
		costoFinal.setForeground(new Color(53,175,64));
		
		usuarioReserva = new JTextField();
		usuarioReserva.setEnabled(false);
		usuarioReserva.setBounds(161,401,578,30);
		usuarioReserva.setFont(fuenteInputFormulario);
		
		reservasPanel.add(fechaReserva);
		reservasPanel.add(cantidadPasajeros);
		reservasPanel.add(costoFinal); 
		reservasPanel.add(usuarioReserva);
		
		
		//agrego key listener para el campo fecha de reserva
		KeyListener fechaKeyListener = new KeyListener() {
			@Override
			public void keyTyped(KeyEvent e) {
				reservaExitosaLabel.setVisible(false);
			}
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void keyPressed(KeyEvent e) {
				// TODO Auto-generated method stub
			}
		};
		fechaReserva.addKeyListener(fechaKeyListener);
		
		
		
		//focus listener para el campo fecha del vuelo
		FocusListener fechaFocusListener = new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				fechaReserva.setText("");
				costoFinal.setText("");
				usuarioReserva.setText("");
				advertenciaCamposReserva.setVisible(false);
				
				avionesDisponibles.removeAllItems();
				avionesDisponibles.setEnabled(false);
				calcularCostoBtn.setEnabled(false);
				confirmarReservaBtn.setEnabled(false);
				usuarioReserva.setEnabled(false);
			}
		};
		fechaReserva.addFocusListener(fechaFocusListener);
		
		//focus listener para el campo de cantidad de pasajeros
		FocusListener cantidadPasajerFocusListener = new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void focusGained(FocusEvent e) {
				cantidadPasajeros.setText("");
				advertenciaCamposReserva.setVisible(false);
				costoFinal.setText("");
				reservaExitosaLabel.setVisible(false);
			}
		};
		cantidadPasajeros.addFocusListener(cantidadPasajerFocusListener);
		
		//focus listener para el campo dni del usuario q hace la reserva
		FocusListener usuarioDniFocusListener = new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void focusGained(FocusEvent e) {
				usuarioReserva.setText("");
				reservaExitosaLabel.setVisible(false);
			}
		};
		usuarioReserva.addFocusListener(usuarioDniFocusListener);
	}
	
	private void cargarReservasComoboBox() {
		origenBox = new JComboBox<String> (ciudadesOrigen);
		origenBox.setFont(fuenteInputFormulario);
		origenBox.setBounds(161,158,254,30);
		
		destinoBox = new JComboBox<String>(ciudadesDestino);
		destinoBox.setFont(fuenteInputFormulario);
		destinoBox.setBounds(485,158,254,30);
		
		avionesDisponibles = new JComboBox<String>();
		avionesDisponibles.setBounds(161,291,578,30);
		avionesDisponibles.setEnabled(false);
		
		reservasPanel.add(origenBox);
		reservasPanel.add(destinoBox);
		reservasPanel.add(avionesDisponibles);
		
		//focus listener para el combo box Origen
		FocusListener origenBoxFocus = new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				advertenciaCamposReserva.setVisible(false);
				costoFinal.setText("");
				reservaExitosaLabel.setVisible(false);
			}
		};
		origenBox.addFocusListener(origenBoxFocus);
		
		//focus listener para el combo box Destino
		FocusListener destinoBoxFocus = new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
			
			@Override
			public void focusGained(FocusEvent e) {
				advertenciaCamposReserva.setVisible(false);
				costoFinal.setText("");
				reservaExitosaLabel.setVisible(false);
			}
		};
		destinoBox.addFocusListener(destinoBoxFocus);
	}
	
	//chequea q sean logicas las selecciones de los combo box Origen-Destino
	private boolean checkRutaReserva(int origen, int destino) {
		//ambos combo box tiene q haber seleccionado una ciudad
		if(origen != 0 && destino != 0)
			//la ciudad de Origen no puede ser igual a la de Destino
			if((origen == 2 && destino == 1) || (origen == 3 && destino == 2))
				return false;
			else	//si Origen y Destino tienen seleccionadas ciudades distintas => TRUE
				return true;
		else
			return false;
	}
	
	//chequea q la cantidad de pasajeros sea un entero mayor a 0
	private boolean checkCantidadPasajerosInput(String cantidadPasajerosString) throws NumberFormatException {
		boolean check = true;
		int cantidadPasajerosInt = Integer.parseInt(cantidadPasajerosString);
		if(cantidadPasajerosInt < 1) {
			check = false;
		}
		return check;
	}
	
	//busca los vuelos compatibles con la fecha,ruta y cantidad de viajeros indicados por el usuario
	//devuele un hashmap con los vuelos compatibles o vacio
	private HashMap<Integer,Vuelo> buscarVuelosCompatibles(Date fecha,String origen,String destino,int cantidadViajeros){
		HashMap<Integer,Vuelo> vuelosCompatibles = new HashMap<Integer,Vuelo>();
		int key = 0;
		for(Vuelo v : vuelos) {
			if(v.getFecha().equals(fecha) && v.getRuta().getOrigen().equals(origen) && v.getRuta().getDestino().equals(destino) && v.getAsientosLibres() >= cantidadViajeros) {
				key++;
				vuelosCompatibles.put(key,v);
			}
		}
		return vuelosCompatibles;
	}
	
	
	
	
	
	//PANEL DE CANCELAR VUELOS
	private void cargarCancelarVueloPanel() {
		cancelacionPanel = new JPanel();
		cancelacionPanel.setVisible(true);
		cancelacionPanel.setLayout(null);
		cancelacionPanel.setOpaque(true);
		cancelacionPanel.setBackground(azul);
		
		//cargo componentes del panel
		cargarCancelacionHeader();
		cargarCancelacionLabels();
		cargarCancelacionTextField();
		cargarCancelacionBotones();
		cargarCancelacionComboBox();
		
		contentPane.add(cancelacionPanel);
	}
	
	private void cargarCancelacionHeader() {
		JLabel labelCancelarHeader= new JLabel ("   Cancelar vuelo");
		labelCancelarHeader.setBounds(0,0,900,50);
		labelCancelarHeader.setOpaque(true);
		labelCancelarHeader.setBackground(oscuro);
		labelCancelarHeader.setFont(fuenteHeader);
		labelCancelarHeader.setForeground(Color.white);
		
		cancelacionPanel.add(labelCancelarHeader);
	}
	
	private void cargarCancelacionLabels() {
		JLabel dniUsuarioCancelacion = new JLabel("DNI del usuario (sin separador de miles)");
		dniUsuarioCancelacion.setBounds(161,140,458,17);
		dniUsuarioCancelacion.setFont(fuenteFormulario);
		dniUsuarioCancelacion.setForeground(Color.white);
		
		JLabel pasajesReservados = new JLabel("Pasajes reservados");
		pasajesReservados.setBounds(161,250,458,17);
		pasajesReservados.setFont(fuenteFormulario);
		pasajesReservados.setForeground(Color.white);
		
		
		mensajeCancelacionConfirmada = new JLabel("Se ha cancelado la reserva");
		mensajeCancelacionConfirmada.setFont(fuenteMensajeDelSistema);
		mensajeCancelacionConfirmada.setForeground(Color.green);
		mensajeCancelacionConfirmada.setVisible(false);
		
		cancelacionPanel.add(pasajesReservados);
		cancelacionPanel.add(dniUsuarioCancelacion);
	}
	
	private void cargarCancelacionTextField() {
		dniUsuarioCancelacion = new JTextField();
		dniUsuarioCancelacion.setFont(fuenteInputFormulario);
		dniUsuarioCancelacion.setBounds(161,160,578,30);
		
		cancelacionPanel.add(dniUsuarioCancelacion);
		
		FocusListener focusDniUsuarioCancelacion = new FocusListener() {
			@Override
			public void focusLost(FocusEvent e) {
				// TODO Auto-generated method stub
			}
			@Override
			public void focusGained(FocusEvent e) {
				dniUsuarioCancelacion.setText("");
				mensajeCancelacionConfirmada.setVisible(false);
				pasajesReservadosComboBox.removeAllItems();
				pasajesReservadosComboBox.setEnabled(false);
				btnConfirmarCancelacion.setEnabled(false);
			}
		};
		dniUsuarioCancelacion.addFocusListener(focusDniUsuarioCancelacion);
	}
	
	private void cargarCancelacionBotones() {
		JButton buscarPasajesReservadosBtn = new JButton("Buscar");
		buscarPasajesReservadosBtn.setBounds(161,200,578,30);
		buscarPasajesReservadosBtn.setOpaque(true);
		buscarPasajesReservadosBtn.setBackground(naranja);
		buscarPasajesReservadosBtn.setFont(fuenteBtn);
		buscarPasajesReservadosBtn.setForeground(Color.white);
		
		JButton btnVolverCancelacion = new JButton("Volver");
		btnVolverCancelacion.setBounds(282,427,134,51);
		btnVolverCancelacion.setOpaque(true);
		btnVolverCancelacion.setBackground(naranja);
		btnVolverCancelacion.setFont(fuenteBtn);
		btnVolverCancelacion.setForeground(Color.white);
		
		btnConfirmarCancelacion = new JButton("Confirmar");
		btnConfirmarCancelacion.setBounds(485,427,134,51);
		btnConfirmarCancelacion.setOpaque(true);
		btnConfirmarCancelacion.setBackground(naranja);
		btnConfirmarCancelacion.setFont(fuenteBtn);
		btnConfirmarCancelacion.setForeground(Color.white);
		btnConfirmarCancelacion.setEnabled(false);
		
		cancelacionPanel.add(btnVolverCancelacion);
		cancelacionPanel.add(btnConfirmarCancelacion);
		cancelacionPanel.add(buscarPasajesReservadosBtn);
		
		//agrego action listener al boton Volver
		ActionListener volverActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				cancelacionPanel.setVisible(false);
				indexPanel.setVisible(true);
			}
		};
		btnVolverCancelacion.addActionListener(volverActionListener);
		
		//agrego action listener al boton Buscar
		ActionListener buscarActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//corroboro q el dni haya sido ingresado
				if(!campoVacio(dniUsuarioCancelacion)) {
					//corroboro q exista un usuario con el dni ingresado
					if(dniExiste(dniUsuarioCancelacion.getText())) {
						//habilito el combo box y si hay vuelos para cancelar, los muestro por este mismo combo box
						pasajesReservadosComboBox.setEnabled(true);
						mostrarVuelosParaCancelar(buscarPasajesDeUsuario(dniUsuarioCancelacion.getText()));
					}else {
						dniUsuarioCancelacion.setText("*El DNI ingresado no corresponde a un usuario registrado*");
					}
				}else {
					dniUsuarioCancelacion.setText("*Complete este campo*");
				}
			}
		};
		buscarPasajesReservadosBtn.addActionListener(buscarActionListener);
		
		//evetno del btn Confirmar -- se confirma la cancelacion de la reserva
		ActionListener confirmarCancelacionActionListener = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//busco el vuelo afectado
				for(Vuelo v : vuelos) {
					if(v.equals(pasajesUsuarioCancelacion.get(pasajesReservadosComboBox.getSelectedIndex()).getVuelo())) {
						//obtengo los asientos q se liberaran con la cancelacion
						int asientosLiberados = pasajesUsuarioCancelacion.get(pasajesReservadosComboBox.getSelectedIndex()).getCantidadDeViajeros();
						//modifico la cantidad de asiento libres para el vuelo
						v.setAsientosLibres(v.getAsientosLibres()+asientosLiberados);
					}
						
				}
				//busco el usuario q ha hecho la cancelacion segun su dni
				for(Usuario u : usuarios) {
					if(u.getDni().equals(dniUsuarioCancelacion.getText())) {
						//recorro los pasajes del usuario en cuestion
						for(Pasaje p : u.getPasajes()) {
							//cuando encuentro el pasaje reservado => cancelo la reserva
							if(p.equals(pasajesUsuarioCancelacion.get(pasajesReservadosComboBox.getSelectedIndex())))
								p.cancelarReserva();
						}
					}
				}
				
				//muestro mensaje de confirmacion
				mensajeCancelacionConfirmada.setVisible(true);
				//vacio campo del dni
				dniUsuarioCancelacion.setText("");
				//limpio combo box
				pasajesReservadosComboBox.removeAllItems();
				pasajesReservadosComboBox.setEnabled(false);
				//inhabilito el btn de confirmar => se volver a habilitar cuando haya una nueva busqueda con vuelos para cancelar
				btnConfirmarCancelacion.setEnabled(false);				
			}
		};
		btnConfirmarCancelacion.addActionListener(confirmarCancelacionActionListener);
	}
	
	
	//busca un usuario segun su dni y devuele un AL<Pasaje> con todos los pasajes factibles a ser cancelados
	//si no tiene ninguno factibles a ser cancelado, devuelve el AL<Pasaje> vacio
	private ArrayList<Pasaje> buscarPasajesDeUsuario(String usuarioDni){
		//vacio el array list de la ultima busqueda
		pasajesUsuarioCancelacion.clear();
		//agrego todos los pasajes del usuario q va a cancelar un vuelo
		Usuario usuarioCancelacion = buscarUsuario(usuarioDni);
		for(Pasaje p : usuarioCancelacion.getPasajes()) {
			if(p.getReservado() == true && checkFechaCancelacion(p.getVuelo()))
				pasajesUsuarioCancelacion.add(p);
		}
		//devuelo un array list con los pasajes factibles a cancelarse
		return pasajesUsuarioCancelacion;
	}
	
	//chequea q el vuelo a cancelar no sea un vuelo del dia de la fecha actual
	private boolean checkFechaCancelacion(Vuelo vueloAcancelar) {
		//obtengo la fecha del vuelo
		Calendar fechaDeVuelo = Calendar.getInstance();
		fechaDeVuelo.setTime(vueloAcancelar.getFecha());
		//obtengo la fecha actual
		Calendar hoy = Calendar.getInstance();
		//si la fecha actual es antes q el dia del vuelo => retorna true
		if(hoy.compareTo(fechaDeVuelo) < 0)
			return true;
		// si la fecha actual es la misma q la fecha del vuelo => false
		// si la fecha actual es luego de la de fecha del vuelo => false (logicamente)
		else
			return false;
	}
	
	//muestro los pasajes factibles a ser cancelados en el combo box para q el usuario eliga uno
	private void mostrarVuelosParaCancelar(ArrayList<Pasaje> pasajesReservados) {
		//si hay al menos un vuelo para ser cancelado, habilito el boton de confirmacion y los muestro por el combo box
		if(pasajesReservados.size()>0) {
			btnConfirmarCancelacion.setEnabled(true);
			for(Pasaje p : pasajesReservados) {
				pasajesReservadosComboBox.addItem(p.toString());
			}
		}else {
			pasajesReservadosComboBox.addItem("-- No hay vuelos para cancelar --");
		}
	}
	
	private void cargarCancelacionComboBox() {
		pasajesReservadosComboBox = new JComboBox<String>();
		pasajesReservadosComboBox.setBounds(161,270,578,30);
		pasajesReservadosComboBox.setEnabled(false);
		
		cancelacionPanel.add(pasajesReservadosComboBox);
	}
	
	private Usuario buscarUsuario(String dni) {
		Usuario usuarioBuscado = new Usuario();
		for(Usuario u : usuarios) {
			if(u.getDni().equals(dni))
				usuarioBuscado = u;
		}
		return usuarioBuscado;
	}
}











































