
import java.io.IOException;
import java.net.URI;
import java.util.ArrayList;

class Handler implements URLHandler {
    ArrayList<String> message = new ArrayList<>();
    
    public String handleRequest(URI url) throws IOException{
        StringBuilder result = new StringBuilder();
        if(url.getPath().equals("/")){
            return "add-message?s=<string>";
        }
        if(url.getPath().contains("/add-message")){
            String[] parameters = url.getQuery().split("=");
            if(parameters[0].equals("s")){
                
                message.add(parameters[1]);

                for(int i = 0; i < message.size(); i++){
                    result.append(message.get(i).toString() + "\n");
                }
                return result.toString();
            }
            else {
                return "Couldn't find query parameter s";
            }
        }
        else {
            return "Don't know how to handle that path!";
        }
    }
}

class StringServer {
    public static void main(String[] args) throws IOException {
        if(args.length == 0){
            System.out.println("Missing port number! Try any number between 1024 to 49151");
            return;
        }
        
        int port = Integer.parseInt(args[0]);

        Server.start(port, new Handler());
    }
}