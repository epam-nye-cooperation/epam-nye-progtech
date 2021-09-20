import com.indvd00m.ascii.render.Render;
import com.indvd00m.ascii.render.api.ICanvas;
import com.indvd00m.ascii.render.api.IContextBuilder;
import com.indvd00m.ascii.render.api.IRender;
import com.indvd00m.ascii.render.elements.PseudoText;

public class Main {
	
	public static void main(String[] args) {
		GreetingGenerator generator = new GreetingGenerator();
		IRender render = new Render();
		IContextBuilder builder = render.newBuilder();
		builder.width(120).height(20);
		builder.element(new PseudoText(generator.generateGreeting()));
		ICanvas canvas = render.render(builder.build());
		String s = canvas.getText();
		System.out.println(s);
	}
	
}