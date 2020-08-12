package TratandoCSV;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Program {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		boolean dirExists = false;
		Scanner sc = new Scanner(System.in);

		System.out.println("Informe o diretório do arquivo: ");
		String source = sc.nextLine();
 
		System.out.println("Informe o nome do arquivo com a extensão: ");
		String file = sc.nextLine();

		File sourcePath = new File(source);

		File[] folders = sourcePath.listFiles(File::isDirectory);

		for (File folder : folders) {
			if (folder.toString().equals(source + "\\out")) {
				dirExists = true;
				continue;
			}
		}

		try (BufferedReader br = new BufferedReader(new FileReader(source+"\\"+file))) {

			System.out.println("Lendo arquivo!");

			String target = source+"\\out\\sumary.csv";
			String line = br.readLine();

			if (!dirExists) {
				boolean success = new File("C:\\Curso\\\\out").mkdir();

				if (!success) {
					System.out.println("Erro ao criar diretório de saída");
					return;
				}
			}

			try (BufferedWriter bw = new BufferedWriter(new FileWriter(target))) {

				while (line != null) {
					String[] splitLine = line.split(",");

					Double vlrTotal = Double.parseDouble(splitLine[1]) * Double.parseDouble(splitLine[2]);
					
					bw.write(splitLine[0]+", "+vlrTotal);
					bw.newLine();					
					line = br.readLine();
				}

			} catch (IOException e) {
				e.printStackTrace();
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println("Leitura finalizada...");
		System.out.println("Arquivo SUMARY.csv salvo em "+source+"\\out");
		sc.close();
	}

}
