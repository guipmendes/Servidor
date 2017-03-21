package servidor;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

/**
 * @author Guilherme Mendes Data 18/03/2017
 */
public class Servidor {

	/**
	 * ServerSocket
	 */
	private ServerSocket _serverSocket = null;

	/**
	 * ClienteSocket
	 */
	private Socket _clientSocket = null;

	/**
	 * Porta
	 */
	private int _port = 0;

	/**
	 *
	 * @param porta_
	 * @throws Exception
	 */
	// Inicializado do Servidor
	public Servidor(int port) throws Exception {

		try {
			// aloca a variavel
			this._port = port;
			int result = 1;			
			while(result == 1){
				result = run();
				
			}

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Fica rodando a rotina que aceita conexoes
	 */
	public int run() {
		try {
                        Date data = new Date(System.currentTimeMillis());
			//Ganha instancia do socket na porta desejada
			_serverSocket = new ServerSocket(this._port);
			System.out.println("Aguardando conexao...");

			//Fica aguardando o pedido de conexao do cliente
			_clientSocket = _serverSocket.accept();
			System.out.println("Conexao recebida de " + _clientSocket.getInetAddress().getHostName());
                        String mensagem =("Conexao recebida de " + _clientSocket.getInetAddress().getHostName()) ;
                        new ArquivoLog(data +" - "+ mensagem);
			//Assim que recebe a conexao do client, envia uma mensagem ao mesmo confirmando a conexao
			ObjectOutputStream outputStream = new ObjectOutputStream(_clientSocket.getOutputStream());
			outputStream.flush();
			//Abre a stream para recebimento da mensagem do client
            ObjectInputStream inputStream = new ObjectInputStream(_clientSocket.getInputStream());
            
            //Envia a mensagem falando que a conexao foi bem sucedida
            String mensagem1 = ("A conexao foi bem sucedida.");
            outputStream.writeObject(mensagem1);
            outputStream.flush();
            
            //Apos enviar a mensagem ao client, extrai o conteudo da mensagem do mesmo
            mensagem1 = (String)inputStream.readObject();
            new ArquivoLog(mensagem1);
            System.out.println("Mensagem vinda do client: " + mensagem1);
            
            //Encerrar conexões
            inputStream.close();
            outputStream.close();
            _serverSocket.close();
            
            
            if(mensagem1.contains("fechar")){
            	return 0;
            }
            
            return 1;
            
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}

	}
}
