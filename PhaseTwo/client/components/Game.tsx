"use client"
import { Add, Magicpen } from 'iconsax-react'
import Image from 'next/image'
import { useEffect, useState } from 'react'

type Puzzle = {
    id: number,
    link: string
}

export default function Game() {
    const [puzzle, setPuzzle] = useState({} as Puzzle);
    const [solution, setSolution] = useState({} as Puzzle);

    const fetchPuzzle = async () => {
//         const res = await fetch('/api/puzzles/suguru/random')
//         const data = await res.json();
//         setPuzzle(data);
//         const card = document.querySelector('.flip-card')
//         if(card?.classList.contains('[transform:rotateY(180deg)]')) {
//             card?.classList.remove('[transform:rotateY(180deg)]')
//         }
//         solvePuzzle(data.id);
setPuzzle({id: 1, content: '0 1 0\n2 0 0\n0 0 2'});
    }

    const solvePuzzle = async (id: number) => {
//         const res = await fetch(`/api/puzzles/suguru/${id}/solve`)
//         const data = await res.json();
//         setSolution(data)
    }

    const flipCard = () => {
        const card = document.querySelector('.flip-card')
        card?.classList.toggle('[transform:rotateY(180deg)]')
    }

    useEffect(() => {
        // fetch /api/puzzles and setImage
        fetchPuzzle()
    }, [])

    return (
    <div className='game flex flex-col lg:flex-row items-center justify-center h-full w-full m-auto'>
        <section className='flex-grow'></section>
        <section className='[perspective:1000px] w-[500px] max-w-[100vw] px-6 lg:px-0 max-h-[calc(100vw-3rem)] h-[500px]'>
            <div className='flip-card [transform-style:preserve-3d] transition-transform duration-[0.8s] relative w-full h-full'>
                <div className='puzzle absolute w-full h-full [-webkit-backface-visibility:hidden] [backface-visibility:hidden]'>
                    { puzzle?.link && 
                        <Image src={`http://localhost:8080/${puzzle.link}`} width='500' height='500' alt={''}></Image>
                    }
                </div>
                <div className='solution absolute w-full h-full [transform:rotateY(180deg)] [-webkit-backface-visibility:hidden] [backface-visibility:hidden]'>
                    {}
                </div>
            </div>
            
        </section>
        <section className='flex flex-col items-center justify-center flex-grow gap-8 pt-12 lg:pt-0'>
          <button onClick={fetchPuzzle} className="btn new-puzzle w-full lg:w-3/4 gap-2 text-primary-content">
            <Add size="24"/>
            New Puzzle
          </button>
          <button onClick={flipCard} className="btn solve-puzzle w-full lg:w-3/4 gap-2 text-primary-content">
            <Magicpen size="24"/>
            Solve
          </button>
        </section>
      </div>
    );
}