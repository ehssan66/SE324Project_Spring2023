import React from 'react'
import { getName } from './Suguru'

export default function SuguruCell({puzzleState, position, onChange}) {

  let value = puzzleState[getName(position)].match(/[0-9]/) ? puzzleState[getName(position)].match(/[0-9]/)[0] : ''

  return (
    <div>
        <input type="text" value={value} onChange={(e)=> onChange(e)} name={getName(position)} maxLength={1}
            className='text-center bg-base-100 p-6 w-16 h-16'
         />
    </div>
  )
}
