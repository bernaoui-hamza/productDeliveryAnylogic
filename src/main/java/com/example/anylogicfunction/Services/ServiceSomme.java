package com.example.anylogicfunction.Services;

import com.example.anylogicfunction.Entities.City;
import com.example.anylogicfunction.Repositories.CityRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.aspectj.weaver.ast.Instanceof;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;
//import com.google.gson.Gson;

@RestController
public class ServiceSomme {

    ArrayList<City> listeVille=new ArrayList();
    private final CityRepository cityRepository;

    public ServiceSomme(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    ArrayList<City> addInList(){
        listeVille.add(new City(1,"Valenciennes",1770.4,10));
        listeVille.add(new City(2,"Paris",100.2,10));
        listeVille.add(new City(3,"Brusselle",30,10));
        return listeVille;
    }



    @GetMapping("/City/sortByName")
    public List<City> sortObjectsByName() {
        List<City> sortedList = addInList().stream()
                .distinct()
                .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());
        return sortedList;
    }
    private boolean validateToken(String token) {

        if (token == null || token.isEmpty()) {
            return false;
        }
    if(token.equals("issam")|| token.equals("chaimaa")|| token.equals("hamza"))
        return true;

        return false;

    }
    @GetMapping("/sum")
    public double sum(@RequestParam("a") double a, @RequestParam("b") double b, HttpServletRequest request) throws Exception {
        String token = request.getParameter("token");

        if (!validateToken(token)) {
             throw new Exception("Token invalide");
        }

        return a + b;
    }
    @PostMapping("/convert")
    public String convertJsonToList(HttpServletRequest request,@RequestBody String jsonString) throws Exception {
        String token = request.getParameter("token");
        if (!validateToken(token)) {
            throw new Exception("Token invalide");
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<List<City>>() {}.getType();
        List<City> cityList = gson.fromJson(jsonString, listType);
        List<City>cities=cityList.stream().sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());
        String json = gson.toJson(cities);
        return json;
        //return cities;
    }

    public static List<?> json1ToList(String jsonString, Class<?> classObjec) {
        List<Object> resultList = new ArrayList<>();
        String[] jsonObjects = jsonString.substring(1, jsonString.length() - 1).split("\\},\\{");

        //Get All Fields of the given Class
        java.lang.reflect.Field[]  listeOfFields = classObjec.getDeclaredFields();
        for(java.lang.reflect.Field oneField:listeOfFields){
            System.out.println("field---"+oneField.getName()+"--Type--"+oneField.getType());
        }

        for (String jsonObject : jsonObjects) {
            Object obj;
            try {
                obj = classObjec.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
                continue;
            }
            String[] fields = jsonObject.split(",");

            for (String field : fields) {

                String[] keyValuePair = field.split(":");
                String key = keyValuePair[0].replaceAll("\\{|\\}|\"", "");
                String value = keyValuePair[1].replaceAll("\\{|\\}|\"", "");

                for(java.lang.reflect.Field selectField:listeOfFields){
                    selectField.setAccessible(true);
                    //System.out.println("field---"+oneField.getName()+"--Type--"+oneField.getType());
                    if(selectField.getName().equals(key))

                    {

                        try {
                            if(selectField.getType()==int.class)
                            selectField.set(obj,Integer.parseInt(value));
                            else if(selectField.getType()==double.class)
                                selectField.set(obj,Double.parseDouble(value));
                           else
                                selectField.set(obj,value);
                        } catch (IllegalAccessException e) {

                        }

                    }


                }




               /* try {
                    java.lang.reflect.Field classField = classObjec.getDeclaredField(key);
                    System.out.println(classField.getType()+" <=le type  "+ classObjec.getDeclaredField(key));

                } catch (Exception e) {
                    e.printStackTrace();
                }*/
            }
            resultList.add(obj);
            //resultList.add(obj);
        }

        return resultList;
    }

    @GetMapping("/sortedCity")
    public String sortedCity( HttpServletRequest request ,@RequestParam("data") String jsonB64) throws Exception {
        String token = request.getParameter("token");
      //  String value=request.getParameter("value");

//        ObjectMapper mapper = new ObjectMapper();
//        //City[] pp1 = mapper.readValue(value, City[].class);
//        List<City> givenCity = Arrays.asList(mapper.readValue(value, City[].class));
        List<City> listCity = new ArrayList<>();
        if (!validateToken(token)) {
            throw new Exception("Token invalide");
        }
        Gson gson = new Gson();
        Type listType = new TypeToken<List<City>>() {}.getType();
        List<City> cityList=null ;
        //City city1=gson.fromJson(jsonB64,City.class);
        for(City c1:cityList)
        System.out.println(c1.getId()+"salaaaam"+c1.getName());
        //ObjectMapper objectMapper = new ObjectMapper();

        // Lire le JSON à partir du fichier et convertir en objets Java
        //City[] myCityArray = objectMapper.readValue(jsonB64, City[].class);
        System.out.println("voila  "+jsonB64);
//for(City  city:myCityArray){
  //  listCity.add(city);
//}
        List<City> sortedList = addInList().stream()
               .sorted(Comparator.comparing(City::getName))
                .collect(Collectors.toList());
        //return sortedList;
//        byte[] jsonBytes = Base64.getDecoder().decode(jsonB64);
//        String json = new String(jsonBytes, StandardCharsets.UTF_8);
//
//        // Faire quelque chose avec le JSON...
//        // Par exemple, le retourner sous forme de chaîne de caractères
//        return "JSON reçu : " + json;
//        String json1 = objectMapper.writeValueAsString(sortedList);
//        byte[] jsonBytes = Base64.getDecoder().decode(json1);
//        String json = new String(jsonBytes, StandardCharsets.UTF_8);

        // Faire quelque chose avec le JSON...
        // Par exemple, le retourner sous forme de chaîne de caractères
        return "JSON reçu : " ;


    }


}