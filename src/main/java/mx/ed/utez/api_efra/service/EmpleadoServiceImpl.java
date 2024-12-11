package mx.ed.utez.api_efra.service;

import mx.ed.utez.api_efra.model.DAO.EmpleadosDao;
import mx.ed.utez.api_efra.model.Empleados;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmpleadoServiceImpl  implements  EmpleadoService{
    @Autowired
    private EmpleadosDao empleadosDao;
    private Empleados empleados;

    @Override
    public List<Empleados> getEmpleados(){
        return (List<Empleados>)  empleadosDao.findAll();
    }

    @Override
    public Empleados getEmpleadoById(Long id){
        Optional<Empleados> empleados = empleadosDao.findById(id);
        return empleados.orElseThrow(() -> new RuntimeException("Empleado no ecnontrado"));
    }

    @Override
    public Empleados saveEmpleado(Empleados empleados){
        return empleadosDao.save(empleados);
    }

    @Override
    public Empleados updateEmpleado(Long id){
        Empleados nuevoEmpleado = getEmpleadoById(id);
        nuevoEmpleado.setId(empleados.getId());
        return empleadosDao.save(nuevoEmpleado);
    }

    @Override
    public boolean deleteEmpleado(Long id){
        if(empleadosDao.existsById(id)){
            empleadosDao.deleteById(id);
            return true;
        }
        return false;
    }
}
