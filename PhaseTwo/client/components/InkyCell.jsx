import React from 'react'
import { getName } from './Inky'

export default function InkyCell({puzzleState, position, onChange}) {
  
  return (
    <div>
         <input type="text" value={puzzleState[getName(position)]} onChange={(e)=> onChange(e)} name={getName(position)} maxLength={1}
            className='text-center bg-base-100 p-6 w-16 h-16'/>
    </div>
  )
}
