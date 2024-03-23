package gui.a7.View;



public class exitCommand extends Command
{
    public exitCommand(String key, String description)
    {
        super(key, description);
    }

    @Override
    public void execute()
    {
        System.exit(0);
    }
}
