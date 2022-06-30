import java.awt.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;

class GUIServer
{
Frame f;
TextField t;
List l;
Button b;
ServerSocket ss;
Socket s;
GUIServer()
{
f=new Frame("Server");
l=new List();
t=new TextField();
b=new Button("Send");
b.addActionListener(new ActionListener()
{
public void actionPerformed(ActionEvent e)
{
try
{
ObjectOutputStream oos=new ObjectOutputStream(s.getOutputStream());
oos.writeObject(t.getText());
l.add("Me: "+t.getText());
}
catch(Exception ee)
{
System.out.println(ee.getMessage());
}
}
});
f.add(t,BorderLayout.NORTH);
f.add(b,BorderLayout.SOUTH);
f.add(l);
f.setSize(400,400);
f.setVisible(true);
try
{
ss=new ServerSocket(2003);
s=ss.accept();
while(true)
{
ObjectInputStream ois=new ObjectInputStream(s.getInputStream());
String str=ois.readObject().toString();
l.add("Friend: "+str);
}
}
catch(Exception e)
{
System.out.println(e.getMessage());
}
}
public static void main(String ar[])
{
GUIServer gs=new GUIServer();
}
}