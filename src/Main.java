import java.io.*;
import java.util.Scanner;
import java.util.regex.Pattern;
    public class Main {
        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            System.out.print("Введите путь к исходному файлу: ");
            String input = scanner.nextLine();
            System.out.print("Введите путь к новому файлу (без комментариев): ");
            String output = scanner.nextLine();
            try {
                removecomments(input, output);
                System.out.println("Комментарии удалены. Результат в: " + output);
            } catch (IOException e) {
                System.err.println("Ошибка: " + e.getMessage());
                e.printStackTrace();
            } finally {
                scanner.close();
            }
        }
        public static void removecomments(String inputFile, String outputFile) throws IOException {
            BufferedReader reader = null;
            BufferedWriter writer = null;
            try {
                reader = new BufferedReader(new FileReader(inputFile));
                writer = new BufferedWriter(new FileWriter(outputFile));
                Pattern lineComm = Pattern.compile("//.*");
                Pattern blockComm = Pattern.compile("/\\*.*?\\*/", Pattern.DOTALL);
                String line;
                StringBuilder fileupdate = new StringBuilder();
                while ((line = reader.readLine()) != null) {
                    fileupdate.append(line).append("\n");
                }
                String content = fileupdate.toString();
                content = blockComm.matcher(content).replaceAll("");
                content = lineComm.matcher(content).replaceAll("");
                writer.write(content);
            } finally {
                if (reader != null) reader.close();
                if (writer != null) writer.close();
            }
        }
    }
