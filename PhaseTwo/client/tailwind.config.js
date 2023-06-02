/** @type {import('tailwindcss').Config} */
module.exports = {
  content: [
    './pages/**/*.{js,ts,jsx,tsx,mdx}',
    './components/**/*.{js,ts,jsx,tsx,mdx}',
    './app/**/*.{js,ts,jsx,tsx,mdx}',
  ],
  theme: {
    extend: {
      backgroundImage: {
        'gradient-radial': 'radial-gradient(var(--tw-gradient-stops))',
        'gradient-conic':
          'conic-gradient(from 180deg at 50% 50%, var(--tw-gradient-stops))',
      },
    },
  },
  daisyui: {
    themes: [
      {
        dark: {
          "primary": "#343232",
          "secondary": "#343232",
          "accent": "#343232",
          "neutral": "#272626",
          "base-100": "#000000",
          "info": "#2563eb",
          "success": "#a3e635",
          "warning": "#fde047",
          "error": "#dc2626",
          "--rounded-box": "0rem", // border radius rounded-box utility class, used in card and other large boxes
          "--rounded-btn": "0rem", // border radius rounded-btn utility class, used in buttons and similar element
          "--rounded-badge": "1rem",
        },
      },
      {
        white: {
          "primary": "#B8B8B8",
          "secondary": "#B8B8B8",
          "accent": "#B8B8B8",
          "neutral": "#EBEBEB",
          "base-100": "#FFFFFF",
          "info": "#0000FF",
          "success": "#008000",
          "warning": "#A6A659",
          "error": "#FF0000",
          "--rounded-box": "0rem", // border radius rounded-box utility class, used in card and other large boxes
          "--rounded-btn": "0rem", // border radius rounded-btn utility class, used in buttons and similar element
          "--rounded-badge": "1rem"
        },
      },
    ],
  },
  plugins: [require("@tailwindcss/typography"), require("daisyui")],
}
