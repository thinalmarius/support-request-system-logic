/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logiclayer;

import data.Datalink;
import data.Datalink_Service;
import data.Tickets;
import java.util.ArrayList;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;

/**
 *
 * @author Thinal
 */
@WebService(serviceName = "logiclink")
public class logiclink {
    
    static Datalink_Service service = new Datalink_Service();
    static Datalink proxy = service.getDatalinkPort();
    
    
    /*public static void main(String args[]){
        String result=checkUserPwd("admin","admen");
        System.out.println(result);
    }*/

    @WebMethod(operationName = "checkUserPsd")
    public String checkUserPwd(@WebParam(name = "name") String name, @WebParam(name = "password") String password) {
        ArrayList<String> users = new ArrayList<String>();
        users = (ArrayList<String>) proxy.getuser();
        
        String pswd=null;
        String result=null;
       
        for(int i=0;i<users.size();i++){
            if (users.get(i).equals(name)){
                pswd = proxy.getPassword(name);
                //System.out.println(pswd);
                if (pswd.equals(password)){
                    result="ok";break;
                }else
                    result=null;

            }else{
                result=null;            
            }
        }
        return result;
        
    }
    @WebMethod(operationName= "getCusId")
    public int getCusId(@WebParam(name = "name") String name, @WebParam(name = "company") String company){
        int id=proxy.getCusId(name, company);
        return id;
    }
    
    @WebMethod(operationName = "getMessage")
    public void getMessage(@WebParam(name = "id")int id,@WebParam(name = "line")String line){
        proxy.getMsg(id,line);
    }
    
    @WebMethod(operationName = "viewTickets")
    public ArrayList<Tickets> viewTickets() {
        ArrayList<Tickets> ticket = new ArrayList<Tickets>();
        ticket=(ArrayList<Tickets>) proxy.viewTickets();
        return ticket;
        
    }
    
}
