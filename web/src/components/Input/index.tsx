import React, { InputHTMLAttributes, useEffect, useRef } from 'react';

import { useField } from '@unform/core';

import  './styles.css';

interface InputProps extends InputHTMLAttributes<HTMLInputElement> {
  label: string;
  name: string
}

const Input: React.FC<InputProps> = ({ label, name, ...rest }) => {
  
  const inputRef = useRef<HTMLInputElement>(null);
  const {fieldName, registerField, error, defaultValue }= useField(name);

  useEffect(() =>{
    registerField({
      name: fieldName,
      ref: inputRef.current,
      path: 'value'
    })
  }, [fieldName, registerField]);

  return (

    <div className="input-block">
      <label htmlFor={ name }>{ label }</label>
      <input ref={inputRef} id={ name } { ...rest } defaultValue={defaultValue} />
      { error && <span style={{color:'#f00'}}>{error}</span>}
    </div>

  );
}

export default Input;