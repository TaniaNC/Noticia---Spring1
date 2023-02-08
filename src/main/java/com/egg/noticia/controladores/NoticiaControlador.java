package com.egg.noticia.controladores;

import com.egg.noticia.entidades.Noticia;
import com.egg.noticia.excepciones.MiException;
import com.egg.noticia.servicios.NoticiaServicio;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/noticia")
/*  localhost:8080/noticia  */
public class NoticiaControlador {

    @Autowired
    NoticiaServicio noticiaService;

    @GetMapping("/registrar")
    public String registrar() {

        return "noticia_form.html";
    }

    @PostMapping("/registro")
    public String registro(@RequestParam String titulo, @RequestParam String cuerpo, ModelMap modelo) {

        try {

            noticiaService.crearNoticia(titulo, cuerpo);
            modelo.put("exito", "La noticia se ha creado correctamente");

        } catch (MiException ex) {

            modelo.put("error", ex.getMessage());
            return "noticia_form.html";

        }
        return "index.html";
    }
    
    @GetMapping("/eliminar")
    public String eliminar(ModelMap modelo){
        
        List <Noticia> noticias = noticiaService.listarNoticias();
        
        modelo.addAttribute("noticias", noticias);
        
        /* HACER ESTO  en HTML PARA ELEGIR LA NOTICIA A ELIMINAR
        <div class="form-group my-3">

                    <select name="idAutor">

                        <option>Seleccionar Autor</option>

                        <option th:each="autor : ${autores}"
                                th:value="${autor.id}"
                                th:text="${autor.nombre}"
                                />
                    </select>
                </div>
*/
        return "noticia_delete.html";
    }

}
