"use client"
import { Moon, Rank, Sun1 } from "iconsax-react";
import Link from "next/link";
import { useState } from "react";

export default function Header() {
    const [theme, setTheme] = useState('dark');

    const switchTheme = () => {
        const theme = document.body.dataset.theme
        if (theme === 'dark') {
            document.body.dataset.theme = 'white'
            setTheme('white')
        } else {
            document.body.dataset.theme = 'dark'
            setTheme('dark')
        }
    }

    return (
        <header className="border-b border-primary">
            <div className="navbar bg-base-100 py-3">
                <div className="w-full max-w-6xl mx-auto">
                    {/* <div className="flex-none">
                        <button className="btn btn-square btn-ghost">
                        <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" className="inline-block w-5 h-5 stroke-current"><path strokeLinecap="round" strokeLinejoin="round" strokeWidth="2" d="M4 6h16M4 12h16M4 18h16"></path></svg>
                        </button>
                    </div> */}
                    <div className="flex-1">
                        <Link href="/" className="btn btn-ghost normal-case text-2xl">
                            SE Club
                            <small className="font-thin italic text-sm pl-1.5 -bottom-px relative">puzzles</small>
                        </Link>
                    </div>
                    <div className="flex-none">
                        <button className="btn btn-square btn-ghost scoreboard text-primary-content" title="Scoreboard">
                            <Rank size="26"/>
                        </button>
                        <button onClick={switchTheme} className="btn btn-square switch-theme btn-ghost text-primary-content" title="Switch Theme">
                            {
                                theme === 'dark' ?
                                <Sun1 size="26" className="text-primary-content"/>
                                : <Moon size="26" className="text-primary-content"/>
                            }
                        </button>
                    </div>
                </div>
            </div>
        </header>
    );
}