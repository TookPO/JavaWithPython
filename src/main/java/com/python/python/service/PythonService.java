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

	public List<Object> useJython(Map<String, String> dataMap) {
		pythonInterpreter = new PythonInterpreter();
		List<Object> dataList = new ArrayList<>();

		// 파이썬 파일 로드
		pythonInterpreter.execfile("src/main/resources/ListFunction.py");
		System.out.println("Python file loaded successfully.");

		// set_data 함수 가져오기
		PyFunction pyFunction = (PyFunction) pythonInterpreter.get("set_data", PyFunction.class);
		if (pyFunction == null) {
			throw new RuntimeException("Failed to read the set_data function");
		}
		System.out.println("set_data function loaded successfully.");

		// set_data 함수 호출
		PyObject pyobj = pyFunction.__call__(new PyString(dataMap.get("fruit")), new PyString(dataMap.get("color")));
		for (PyObject obj : pyobj.asIterable()) {
			dataList.add(obj.toString());
		}

		return dataList;

	}

}
