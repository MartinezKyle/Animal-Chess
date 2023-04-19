public class MCO2
{
    public static void main(String[] args) {
        AnimalChess game = new AnimalChess();
		Controller cont = new Controller (game, new View(game));
    } 
}