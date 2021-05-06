package mc322.lab05;

public class AppDama {

    public static String[] executaJogo(String commandsCSVPath, String outCSVPath) {
        CSVHandling csvHandling = new CSVHandling();
        csvHandling.setDataSource(commandsCSVPath);
        String[] commands = csvHandling.requestCommands();

        String[] result = new String[commands.length + 1];

        Board board = new Board();

        System.out.println("Initial board:");
        board.imprimirTabuleiro();
        System.out.println();
        result[0] = board.getStateString();

        boolean hasError = false;
        for (int i = 0; i < commands.length; i++) {
            String command = commands[i];
            Position source = new Position(command.substring(0, 2));
            Position destination = new Position(command.substring(3, 5));
            // System.out.println("Command: move from " + source.toString() + " to " + destination.toString());
            if (board.solicitaMovimento(source, destination) == false) {
                System.out.println("Movimento invalido!");
                hasError = true;
            }
            board.imprimirTabuleiro();
            System.out.println();
            result[i+1] = board.getStateString();
        }

        if (!hasError) {
            board.exportarArquivo(outCSVPath);
        } else {
            board.exportarArquivoErro(outCSVPath);
        }

        return result;
    }

    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("args: <path-to-commands-csv> <path-to-output-file>");
        } 
        
        executaJogo(args[0], args[1]);
    }
}
