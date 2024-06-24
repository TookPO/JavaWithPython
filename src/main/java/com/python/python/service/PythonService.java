package com.python.python.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.python.core.PyFunction;
import org.python.core.PyObject;
import org.python.core.PyString;
import org.python.util.PythonInterpreter;
import org.springframework.stereotype.Service;

@Service
public class PythonService {
	
	private static PythonInterpreter pythonInterpreter;
	
	public List<Object> useJython(Map<String, String> dataMap){
		pythonInterpreter = new PythonInterpreter();
		List<Object> dataList = new ArrayList<>();
		
		PyFunction pyFunction = (PyFunction) pythonInterpreter.get("set_data", PyFunction.class);
		PyObject pyobj = pyFunction.__call__(new PyString(dataMap.get("color")), new PyString(dataMap.get("fruit")));
		
		for(PyObject obj : pyobj.asIterable()) {
			dataList.add(obj.toString());
		}
		
		return dataList;
	}
	
}
