import { useField } from '@unform/core';
import React, { SelectHTMLAttributes, useEffect, useRef } from 'react';

import  './styles.css';

interface SelectProps extends SelectHTMLAttributes<HTMLSelectElement> {
  label: string;
  name: string;
  options: Array<{
    value: number;
    label: string
  }> ;
}

const Select: React.FC<SelectProps> = ({ label, name, options, ...rest }) => {

  const selectRef = useRef<HTMLSelectElement>(null)
  const { fieldName, registerField, defaultValue } = useField(name);

  useEffect(() =>{
    registerField({
      name: fieldName,
      ref: selectRef.current,
      path: 'value'
    })
  }, [fieldName, registerField]);
  return (

    <div className="select-block">
      <label htmlFor={ name }>{ label }</label>
      <select  id={ name } { ...rest } ref={selectRef}>
        {options.map(option => {
          return <option 
            key={option.value} 
            value={option.value} 
            defaultValue={defaultValue}>{option.label}</option>
        })}
      </select>
    </div>

  );
}

export default Select;